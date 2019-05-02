#!/bin/bash

PROJECT=$1
PATH_TO_CLASSES=$2

## this directory
export DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

RANDOOP_JAR=${DIR}/libs/randoop/randoop-all-4.1.1.jar

(cd ${DIR}/test_programs/${PROJECT};
 
 find....
)

java -jar gentests --classlist=

