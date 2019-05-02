#!/bin/bash

PROJECT=$1
PATH_TO_CLASSES=$2
TIMEOUT=#3
SEED=#4

## this directory
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

RANDOOP_JAR=${DIR}/libs/randoop/randoop-all-4.1.1.jar


CLASSLIST="${DIR}/list-of-classes.txt"
(cd ${DIR}/test_programs/${PROJECT}/${PATH_TO_CLASSES};

 find . -name "*.class" | sed 's/\.class//g' | sed 's/\.\///g' | sed 's/\//./g' > ${CLASSLIST}

 java -ea -cp .:${RANDOOP_JAR} randoop.main.Main gentests --randomseed=${SEED} --classlist=${CLASSLIST} --output-limit=${TIMEOUT} 

)


