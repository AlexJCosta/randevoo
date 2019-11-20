#!/bin/bash

LOCAL_TIMEOUT_EVOSUITE=1
GLOBAL_TIMEOUT_RANDOOP=120
DIR_SF_110="${HOME}/Downloads/SF110-20130704"

#PROJECTS=$DIR_SF_110/projects.txt

for i  in "106_checkstyle"; do
    ./run-randoop-evosuite-with-libs.sh ${DIR_SF_110} "$i" $LOCAL_TIMEOUT_EVOSUITE $GLOBAL_TIMEOUT_RANDOOP 123 randoop
    ./run-randoop-evosuite-with-libs.sh ${DIR_SF_110} "$i" $LOCAL_TIMEOUT_EVOSUITE $GLOBAL_TIMEOUT_RANDOOP 123 evosuite
    ./run-randoop-evosuite-with-libs.sh ${DIR_SF_110} "$i" $LOCAL_TIMEOUT_EVOSUITE $GLOBAL_TIMEOUT_RANDOOP 123 randoop+evosuite
done

#for i  in "92_jcvi-javacommon"; do
    #./run-randoop-evosuite.sh ${DIR_SF_110} "$i" $LOCAL_TIMEOUT_EVOSUITE $GLOBAL_TIMEOUT_RANDOOP 123 randoop
    #./run-randoop-evosuite.sh ${DIR_SF_110} "$i" $LOCAL_TIMEOUT_EVOSUITE $GLOBAL_TIMEOUT_RANDOOP 123 evosuite
    #./run-randoop-evosuite.sh ${DIR_SF_110} "$i" $LOCAL_TIMEOUT_EVOSUITE $GLOBAL_TIMEOUT_RANDOOP 123 randoop+evosuite
#done
