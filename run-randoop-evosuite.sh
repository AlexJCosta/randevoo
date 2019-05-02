#!/bin/bash

## this directory
export DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"


RANDOOP_JAR=${DIR}/libs/randoop/randoop-all-4.1.1.jar
java -jar gentests --classlist=

