#!/bin/bash

PROJECT=$1
PATH_TO_CLASSES=$2
TIMEOUT=$3
SEED=$4

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
 (cd target/classes;

  RANDOOP_DIR=${DIR}/output-tests/randoop-${PROJECT}-`date +%s`
  mkdir ${RANDOOP_DIR}

  ## generating list of classes
  find . -name "*.class" | sed 's/\.class//g' | sed 's/\.\///g' | sed 's/\//./g' > ${CLASSLIST}
  java -ea -cp .:$CP_DEP_CLASSES:$RANDOOP_JAR \
       randoop.main.Main gentests \
       --randomseed=${SEED} \
       --classlist=${CLASSLIST} \
       --output-limit=${TIMEOUT} \
       --junit-output-dir=${RANDOOP_DIR} \
       --junit-package-name=synapse


  ## defining some important paths
  JACOCO_DIR=${DIR}/libs/jacoco
  JACOCO_AGENT=${JACOCO_DIR}/jacocoagent.jar
  JACOCO_CLI=${JACOCO_DIR}/jacococli.jar
  JUNIT_JARS=${DIR}/libs/junit/hamcrest-core-1.3.jar:${DIR}/libs/junit/junit-4.13-beta-2.jar
  
  #########
  ## compile randoop-generated tests and produce coverage
  #########
  (cd ${RANDOOP_DIR};

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

  # ## iterating through every class in the project
  # while IFS= read -r file
  # do
  #     java -jar ${EVOSUITE_JAR} \
  #          -generateSuite \
  #          -Dsearch_budget=${TIMEOUT} \
  #          -Dstopping_condition=MaxTime \
  #          -class $file \
  #          -projectCP .:$CP_DEP_CLASSES
      
  # done < "$CLASSLIST"
  # rm $CLASSLIST
  
 )

)
