#!/bin/bash

SEED=$1
LOCAL_TIMEOUT_EVOSUITE=$2
GLOBAL_TIMEOUT_RANDOOP=$3

########################################
## THIS NEEDS TO BE ADJUSTED PER PROJECT
########################################
DIR_SF_110="${HOME}/Downloads/SF110-20130704"
PROJECT="1_tullibee"

./run-randoop-evosuite.sh ${DIR_SF_110} ${PROJECT} $LOCAL_TIMEOUT_EVOSUITE $GLOBAL_TIMEOUT_RANDOOP $SEED evosuite
./run-randoop-evosuite.sh ${DIR_SF_110} ${PROJECT} $LOCAL_TIMEOUT_EVOSUITE $GLOBAL_TIMEOUT_RANDOOP $SEED randoop

