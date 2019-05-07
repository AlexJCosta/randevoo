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
OUTPUT_DIR=${DIR}/output-tests/${ALGO}-${PROJECT}-`date +%s`
mkdir ${OUTPUT_DIR}

## change directory to project directory
cd $DIR_SF_110/${PROJECT};

## setting project classpath
PROJECT_JAR=$(echo "$PROJECT" | cut -f2 -d\_)".jar"
PROJECT_CLASSPATH="$DIR_SF_110/${PROJECT}/$PROJECT_JAR"
for x in `find $DIR_SF_110/${PROJECT}/lib -name "*.jar"`;
do
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
        
        java -ea -cp .:$PROJECT_JAR:$RANDOOP_JAR \
             randoop.main.Main gentests \
             --classlist=${TOBETESTED} \
             --randomseed=${SEED} \
             --time-limit=${GLOBAL_TIMEOUT_RANDOOP} \
             --junit-output-dir=${OUTPUT_DIR} \
             --junit-package-name=synapse \
             --flaky-test-behavior="OUTPUT"
        
        ;;
    
    "evosuite")

        find $DIR_SF_110/${PROJECT}/evosuite-tests/ -name "*.java" | xargs rm

        while IFS= read -r classfile
        do
            java -jar ${EVOSUITE_JAR} -generateSuite \
                 -class=$classfile \
                 -target=${PROJECT_JAR} \
                 -seed=${SEED} \
                 -criterion=branch \
   	           -Dsearch_budget=${LOCAL_TIMEOUT_EVOSUITE} \
	              -Dstopping_condition=MaxTime \
                 -Dno_runtime_dependency=true
        done < "${TOBETESTED}"
        
        ## Moving tests to output_dir
        mv $DIR_SF_110/${PROJECT}/evosuite-tests/ ${OUTPUT_DIR}
        
	     ;;
    
	 *)
        echo "Fatal error. Should not reach this point!"
        exit 1
	     ;;
    
esac

cd $DIR
./run-coverage.sh ${OUTPUT_DIR} ${PROJECT} ${DIR_SF_110}
