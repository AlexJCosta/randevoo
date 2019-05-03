#!/bin/bash

PROJECT="lattes-analysis"
PATH_TO_CLASSES="/target/classes"
GLOBAL_TIMEOUT_RANDOOP=30 ## 330
LOCAL_TIMEOUT_EVOSUITE=1
SEED=412397

#./run-randoop-evosuite.sh ${PROJECT} ${PATH_TO_CLASSES} ${GLOBAL_TIMEOUT_RANDOOP} ${LOCAL_TIMEOUT_EVOSUITE} ${SEED} "randoop"
#./run-randoop-evosuite.sh ${PROJECT} ${PATH_TO_CLASSES} ${GLOBAL_TIMEOUT_RANDOOP} ${LOCAL_TIMEOUT_EVOSUITE} ${SEED} "evosuite"




#####################
## This is not reliable! For example, EvoSuite coverage says it covers
## 8 of the 8 branches in class PublicationType whereas jacoco says
## there are no brnaches in this class. In fact, there are no branches
## in the source code of this class. Maybe EvoSuite is using some
## bytecode instrumentation. In any case, we can't rely on completely
## different ways of measuring coverage! My suggestion is to run the
## EvoSuite-generated tests agains using jacoco instrumentation as we
## are doing for Randoop. Then, you can run the following code to
## compare stuff. -Marcelo
#####################
MOST_RECENT_RANDOOP=$(ls -d -t output-tests/randoop-* | head -n 1)/jacoco.csv
MOST_RECENT_EVOSUITE=$(ls -d -t output-tests/evosuite-* | head -n 1)/evosuite-report/statistics.csv
echo "============= Randoop ============"
echo "Class, Coverage"
## just show classes with branches to cover
awk -F "," '{print $2"."$3, $6, $7}' $MOST_RECENT_RANDOOP | grep -vE "0 0" | grep -vE "PACKAGE" | sort | awk -F " " '{print $1 ", " 100*$3/($2+$3)}' 
echo "============= EvoSuite ============"
echo "Class, Coverage"
awk -F "," '{print $1 ", " 100*$3}' $MOST_RECENT_EVOSUITE | grep -vE "TARGET" | sort
