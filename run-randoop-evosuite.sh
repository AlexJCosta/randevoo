#!/bin/bash

PROJECT=$1
PATH_TO_CLASSES=$2
GLOBAL_TIMEOUT_RANDOOP=$3
LOCAL_TIMEOUT_EVOSUITE=$4
SEED=$5
ALGO=$6

if [[ -z "$PROJECT" || -z "$PATH_TO_CLASSES" || -z "$GLOBAL_TIMEOUT_RANDOOP" || -z "$LOCAL_TIMEOUT_EVOSUITE" || -z "$SEED" || -z "$ALGO" ]];

then
    echo "missing variable. please check and set before calling!"
    echo PROJECT=$PROJECT
    echo PATH_TO_CLASSES=$PATH_TO_CLASSES
    echo GLOBAL_TIMEOUT_RANDOOP=$GLOBAL_TIMEOUT_RANDOOP
    echo LOCAL_TIMEOUT_EVOSUITE=$LOCAL_TIMEOUT_EVOSUITE
    echo SEED=$SEED
    echo ALGO=$ALGO
    exit
fi

## this directory
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

RANDOOP_JAR=${DIR}/libs/randoop/randoop-all-4.1.1.jar
EVOSUITE_JAR=${DIR}/libs/evosuite/evosuite-1.0.6.jar

(cd ${DIR}/test_programs/${PROJECT};

 ## build project
 mvn install

 ## generate classpaths
 mvn dependency:build-classpath -Dmdep.outputFile=cp.txt
 CP_DEP_CLASSES=$(cat cp.txt)
 rm cp.txt
 PROJECT_CLASSES=${DIR}/test_programs/${PROJECT}/target/classes
 
 CLASSLIST="${DIR}/list-of-classes.txt"

 ## entering maven-generated class directory
 (cd ${PROJECT_CLASSES};

  OUTPUT_DIR=${DIR}/output-tests/${ALGO}-${PROJECT}-`date +%s`
  mkdir ${OUTPUT_DIR}

  ## generating list of classes
  find . -name "*.class" | sed 's/\.class//g' | sed 's/\.\///g' | sed 's/\//./g' > ${CLASSLIST}

  case $ALGO in
      "randoop")
          java -ea -cp .:$CP_DEP_CLASSES:$RANDOOP_JAR \
               randoop.main.Main gentests \
               --randomseed=${SEED} \
               --classlist=${CLASSLIST} \
               --time-limit=${GLOBAL_TIMEOUT_RANDOOP} \
               --junit-output-dir=${OUTPUT_DIR} \
               --junit-package-name=synapse \
               --flaky-test-behavior="OUTPUT"


            ## defining some important paths
          JACOCO_DIR=${DIR}/libs/jacoco
          JACOCO_AGENT=${JACOCO_DIR}/jacocoagent.jar
          JACOCO_CLI=${JACOCO_DIR}/jacococli.jar
          JUNIT_JARS=${DIR}/libs/junit/hamcrest-core-1.3.jar:${DIR}/libs/junit/junit-4.13-beta-2.jar
          
          #########
          ## compile randoop-generated tests and produce coverage
          #########
          (cd ${OUTPUT_DIR};
           
           ## first, compile tests
           find . -name "*.java" | xargs javac -cp $PROJECT_CLASSES:$CP_DEP_CLASSES -d . 
           
           ## then, generate coverage (assuming RegressionTest0 contains everything)
           ## executing the test as to generate coverage log (jacoco.exec)
           #echo "java -cp $JUNIT_JARS:$PROJECT_CLASSES:$CP_DEP_CLASSES -javaagent:${JACOCO_AGENT} org.junit.runner.JUnitCore synapse.RegressionTest"
           java -cp .:$JUNIT_JARS:$PROJECT_CLASSES:$CP_DEP_CLASSES -javaagent:${JACOCO_AGENT} org.junit.runner.JUnitCore synapse.RegressionTest
           
           java -jar $JACOCO_CLI report jacoco.exec \
                --classfiles $PROJECT_CLASSES \
                --csv jacoco.csv
           rm jacoco.exec
           
           ## stat
           awk -F "," '{print $2,$6,$7}' jacoco.csv
           awk -F "," 'BEGIN {SUM1=0;SUM2=0}; {SUM1=SUM1+$6;SUM2=SUM2+$7}; END {printf "uncovered=%.3f covered=%.3f coverage=%.3f\n", SUM1, SUM2, 100*SUM2/(SUM1+SUM2)}' jacoco.csv
           
          )
          
          ;;
      
      "evosuite")

          echo "running evosuite for some time (be patient)"
          #          (
          java -jar ${EVOSUITE_JAR} -generateSuite \
               -target=${PROJECT_CLASSES} \
               -seed=${SEED} \
               -criterion=branch -Dsearch_budget=${LOCAL_TIMEOUT_EVOSUITE} -Dstopping_condition=MaxTime
          
          #          ) >/dev/null 2>&1
          
          (
              echo "number of classes:" $(grep -ve "TARGET" statistics.csv | wc -l)
              grep -vE "TARGET" statistics.csv | awk -F "," '{print $1, $3}' > /tmp/aux
              awk -F " " 'BEGIN {SUM=0}; {SUM=SUM+$2}; END {printf "coverage=%.3f\n", 100*SUM/NR}' /tmp/aux
          ) > average-cov.txt
          rm /tmp/aux
          
	       mv ${PROJECT_CLASSES}/evosuite-tests/ ${OUTPUT_DIR}
          mv ${PROJECT_CLASSES}/evosuite-report/ ${OUTPUT_DIR}          
          ;;
      
      *)
          echo "Fatal error. Should not reach this point!"
          exit 1
      ;;
  esac
  
 ) ## leaving ${PROJECT_CLASSES};
 
) ## leaving ${DIR}/test_programs/${PROJECT};
