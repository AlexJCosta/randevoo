#!/bin/bash

DIR_SF_110=$1
PROJECT=$2
LOCAL_TIMEOUT_EVOSUITE=$3
GLOBAL_TIMEOUT_RANDOOP=$4
SEED=$5
ALGO=$6

if [[ -z "$PROJECT" || -z "$DIR_SF_110" || -z "$LOCAL_TIMEOUT_EVOSUITE" || -z "GLOBAL_TIMEOUT_RANDOOP" || -z "$SEED" || -z "$ALGO" ]];
then
    echo "missing variable. please check and set before calling!"
    echo DIR_SF_110=$DIR_SF_110
    echo PROJECT=$PROJECT
    echo LOCAL_TIMEOUT_EVOSUITE=$LOCAL_TIMEOUT_EVOSUITE
    echo GLOBAL_TIMEOUT_RANDOOP=$GLOBAL_TIMEOUT_RANDOOP
    echo SEED=$SEED
    echo ALGO=$ALGO
    exit
fi

## this directory
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
RANDOOP_JAR=${DIR}/libs/randoop/randoop-all-4.1.1.jar
EVOSUITE_JAR=${DIR}/libs/evosuite/evosuite-1.0.6.jar

## output directory
TIMESTAMP=`date +%s`
OUTPUT_DIR=${DIR}/output-tests/${ALGO}-${PROJECT}-$TIMESTAMP
mkdir ${OUTPUT_DIR}

## change directory to project directory
cd $DIR_SF_110/${PROJECT};

## setting project classpath
PROJECT_JAR=$(echo "$PROJECT" | cut -f2 -d\_)".jar"
PROJECT_CLASSPATH="$DIR_SF_110/${PROJECT}/$PROJECT_JAR"
for x in `find $DIR_SF_110/${PROJECT}/lib -name "*.jar"`;
do
    SOURCE=$x:${SOURCE}
    PROJECT_CLASSPATH=$x:${PROJECT_CLASSPATH}
done
 
## defining some important paths
JACOCO_DIR=${DIR}/libs/jacoco
JACOCO_AGENT=${JACOCO_DIR}/jacocoagent.jar
JACOCO_CLI=${JACOCO_DIR}/jacococli.jar
JUNIT_JARS=${DIR}/libs/junit/hamcrest-core-1.3.jar:${DIR}/libs/junit/junit-4.13-beta-2.jar

## list of files to check coverage
TOBETESTED=${OUTPUT_DIR}/files_to_test.txt
grep "${PROJECT}" "$DIR_SF_110/classes.txt" | cut -f2 -d"	" | grep -v -e '^$' > $TOBETESTED
## entering maven-generated class directory

case $ALGO in
    
    "randoop")
	echo "---> "$SOURCE
        DIR_RANDOOP=${DIR}/output-tests/randoop-${PROJECT}-$TIMESTAMP
        mkdir ${DIR_RANDOOP}
        
        ## Entire list of files in a particular project, for use with Randoop
        TOBETESTED_RANDOOP=${DIR_RANDOOP}/files_to_test.txt
        jar tf ${PROJECT_JAR} | grep .class | sed 's/.class//g'| sed 's/\//./g' >>$TOBETESTED_RANDOOP

        java -ea -cp .:$PROJECT_JAR:$RANDOOP_JAR:$SOURCE \
             randoop.main.Main gentests \
             --classlist=${TOBETESTED_RANDOOP} \
             --randomseed=${SEED} \
             --time-limit=${GLOBAL_TIMEOUT_RANDOOP} \
             --junit-output-dir=${DIR_RANDOOP} \
             --junit-package-name=synapse \
             --flaky-test-behavior="OUTPUT" \
             --usethreads=true \
             --call-timeout=5000

        (cd $DIR;
         ./run-coverage.sh ${OUTPUT_DIR} ${PROJECT} ${DIR_SF_110}
         )
        
        ;;
    
    "evosuite")
        ## delete old test files
        find $DIR_SF_110/${PROJECT}/evosuite-tests/ -name "*.java" | xargs rm

        while IFS= read -r classfile
        do
            java -jar ${EVOSUITE_JAR} -generateSuite \
                 -class=$classfile \
                 -target=${PROJECT_JAR} \
                 -seed=${SEED} \
                 -projectCP=${SOURCE} \
                 -criterion=branch \
                 -Dsearch_budget=${LOCAL_TIMEOUT_EVOSUITE} \
                 -Dstopping_condition=MaxTime \
                 -Dno_runtime_dependency=true \
                 -Dshow_progress=false
        done < "${TOBETESTED}"

        ## Moving tests to output_dir
        mv $DIR_SF_110/${PROJECT}/evosuite-tests/ ${OUTPUT_DIR}

        (cd $DIR;
         ./run-coverage.sh ${OUTPUT_DIR} ${PROJECT} ${DIR_SF_110}
        )

        ##################################################
        # preparing to run randoop after evosuite
        #
        #  (1) create factory methods for every subsequence
        #  (2) compile
        #  (3) run randoop considering those subsequences as regular files
        #  (4) compute coverage
        ##################################################
        ## generating test with all test subsequences
        tmpfile=/tmp/tests.txt
        (cd ${OUTPUT_DIR};
         ## create file with list of paths to test files
         echo >$tmpfile # clear file
         for x in `find . -name "*.java" | sed 's/\.\///g' | grep -vE "ToRandoop"`
         do
             echo ${OUTPUT_DIR}/$x >>$tmpfile ## append to file
         done
        )
        ## build evosuite processor (fast)
        (cd ${DIR}/junit-parser;
         gradle clean fatjar
        )
        SUBDIR=${OUTPUT_DIR}-sub
        mkdir -p $SUBDIR
        (cd $SUBDIR;
         ## generate file!
         java -jar ${DIR}/junit-parser/build/libs/evosuite-testprocessor.jar Main $tmpfile $SOURCE"$DIR_SF_110/${PROJECT}/$PROJECT_JAR"
         
	 echo "### COMPILING THIS FACTORY TESTS ###"
         ## compile
         CP=${PROJECT_JAR}:${PROJECT_CLASSPATH}:${SOURCE}:${JUNIT_JARS}:${EVOSUITE_JAR}
         find . -name "*.java" | xargs javac -cp $CP -d .

        )
	
	echo "###### CALLING RANDOOP ######"
        ## calling randoop
        echo "fromevosuite.ToRandoop" >> $TOBETESTED
        OUTPUT_DIR=${DIR}/output-tests/randoop_after_evosuite-${PROJECT}-$TIMESTAMP
        mkdir -p $OUTPUT_DIR
        cp $TOBETESTED $OUTPUT_DIR
        java -ea -cp .:$PROJECT_JAR:$RANDOOP_JAR:${EVOSUITE_JAR}:${SUBDIR}:$SOURCE \
             randoop.main.Main gentests \
             --classlist=${TOBETESTED} \
             --randomseed=${SEED} \
             --time-limit=${GLOBAL_TIMEOUT_RANDOOP} \
             --junit-output-dir=${OUTPUT_DIR} \
             --junit-package-name=synapse \
             --flaky-test-behavior="OUTPUT" \
	     --usethreads=true \
	     --call-timeout=5000

        
        echo "#########################"
	echo "## jacoco csv"
	echo "#########################"
        (cd ${OUTPUT_DIR};
            ## compile all tests
            CP=.:$PROJECT_JAR:$RANDOOP_JAR:${EVOSUITE_JAR}:${SOURCE}:${SUBDIR}:${PROJECT_CLASSPATH}:${JUNIT_JARS}:${EVOSUITE_JAR}
            find . -name "*.java" | xargs javac -cp $CP -d .
            java -cp ${CP} -javaagent:${JACOCO_AGENT} org.junit.runner.JUnitCore synapse.RegressionTest
            
            if [ -z ${SOURCE} ]
            then
            java -jar $JACOCO_CLI report jacoco.exec \
            --classfiles ${PROJECT_CLASSPATH} \
            --csv jacoco.csv  
            fi
            java -jar $JACOCO_CLI report jacoco.exec \
            --classfiles "$DIR_SF_110/${PROJECT}/$PROJECT_JAR" \
            --csv jacoco.csv
            rm jacoco.exec   
        )
                
	     ;;
    
    "randoop+evosuite")

        DIR_RANDOOP=${DIR}/output-tests/randoop-${PROJECT}-$TIMESTAMP
        mkdir ${DIR_RANDOOP}
        
        ## Entire list of files in a particular project, for use with Randoop
        TOBETESTED_RANDOOP=${DIR_RANDOOP}/files_to_test.txt
        jar tf ${PROJECT_JAR} | grep .class | sed 's/.class//g'| sed 's/\//./g' >>$TOBETESTED_RANDOOP

        java -ea -cp .:$PROJECT_JAR:$SOURCE:$RANDOOP_JAR \
             randoop.main.Main gentests \
             --classlist=${TOBETESTED_RANDOOP} \
             --randomseed=${SEED} \
             --time-limit=${GLOBAL_TIMEOUT_RANDOOP} \
             --junit-output-dir=${OUTPUT_DIR} \
             --junit-package-name=synapse \
             --flaky-test-behavior="OUTPUT" \
             --usethreads=true \
             --call-timeout=5000

        find $DIR_SF_110/${PROJECT}/evosuite-tests/ -name "*.java" | xargs rm

        while IFS= read -r classfile
        do
            java -jar ${EVOSUITE_JAR} -generateSuite \
                 -class=$classfile \
                 -target=${PROJECT_JAR} \
                 -seed=${SEED} \
                 -projectCP=${SOURCE} \
                 -criterion=branch \
                 -Dsearch_budget=${LOCAL_TIMEOUT_EVOSUITE} \
                 -Dstopping_condition=MaxTime \
                 -Dno_runtime_dependency=true \
                 -Dshow_progress=false
        done < "${TOBETESTED}"

        ## Moving tests to output_dir
        mv $DIR_SF_110/${PROJECT}/evosuite-tests/ ${OUTPUT_DIR}


        (cd $DIR;
         ./run-coverage.sh ${OUTPUT_DIR} ${PROJECT} ${DIR_SF_110}
        )
        
        ;;

    
	 *)
        echo "Fatal error. Should not reach this point!"
        exit 1
	     ;;
    
esac

