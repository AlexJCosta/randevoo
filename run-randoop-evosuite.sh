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

 ## generate classpath
 mvn dependency:build-classpath -Dmdep.outputFile=cp.txt
 CP=$(cat cp.txt)
 rm cp.txt

 CLASSLIST="${DIR}/list-of-classes.txt"

 ## entering maven-generated class directory
 (cd target/classes;

  ## generating list of classes
  find . -name "*.class" | sed 's/\.class//g' | sed 's/\.\///g' | sed 's/\//./g' > ${CLASSLIST}
  #echo "java -ea -cp $CP:${RANDOOP_JAR} randoop.main.Main gentests --randomseed=${SEED} --classlist=${CLASSLIST} --output-limit=${TIMEOUT}"
  java -ea -cp .:$CP:$RANDOOP_JAR randoop.main.Main gentests --randomseed=${SEED} --classlist=${CLASSLIST} --output-limit=${TIMEOUT}

  ## iterating through every class in the project
  while IFS= read -r file
  do
      java -jar ${EVOSUITE_JAR} -generateSuite -Dsearch_budget=${TIMEOUT} -Dstopping_condition=MaxTime -class $file -projectCP .:$CP
  done < "$CLASSLIST"
  rm $CLASSLIST  
 )

)
