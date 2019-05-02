#!/bin/bash

PROJECT="lattes-analysis"
PATH_TO_CLASSES="/target/classes"
TIMEOUT=60
SEED=412397

./run-randoop-evosuite.sh ${PROJECT} ${PATH_TO_CLASSES} ${TIMEOUT} ${SEED} "randoop"
#./run-randoop-evosuite.sh ${PROJECT} ${PATH_TO_CLASSES} ${TIMEOUT} ${SEED} "evosuite"
