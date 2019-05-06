#!/bin/bash

PROJECT="lattes-analysis"
PATH_TO_CLASSES="/target/classes"
GLOBAL_TIMEOUT_RANDOOP=30 ## 330
LOCAL_TIMEOUT_EVOSUITE=1
SEED=412397

./run-randoop-evosuite.sh ${PROJECT} ${PATH_TO_CLASSES} ${GLOBAL_TIMEOUT_RANDOOP} ${LOCAL_TIMEOUT_EVOSUITE} ${SEED} "randoop"
./run-randoop-evosuite.sh ${PROJECT} ${PATH_TO_CLASSES} ${GLOBAL_TIMEOUT_RANDOOP} ${LOCAL_TIMEOUT_EVOSUITE} ${SEED} "evosuite"

## sumary of coverage
MOST_RECENT_RANDOOP=$(ls -d -t output-tests/randoop-* | head -n 1)/jacoco.csv
MOST_RECENT_EVOSUITE=$(ls -d -t output-tests/evosuite-* | head -n 1)/jacoco.csv
echo "============= Randoop ============"
echo "Class, Coverage"
## just show classes with branches to cover
awk -F "," '{print $2"."$3, $6, $7}' $MOST_RECENT_RANDOOP | grep -vE "0 0" | grep -vE "PACKAGE" | sort | awk -F " " '{print $1 ", " 100*$3/($2+$3)}' 
echo "============= EvoSuite ============"
echo "Class, Coverage"
awk -F "," '{print $2"."$3, $6, $7}' $MOST_RECENT_EVOSUITE | grep -vE "0 0" | grep -vE "PACKAGE" | sort | awk -F " " '{print $1 ", " 100*$3/($2+$3)}' 
#awk -F "," '{print $1 ", " 100*$3}' $MOST_RECENT_EVOSUITE | grep -vE "TARGET" | sort
