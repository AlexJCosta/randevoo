#!/bin/bash

LOCAL_TIMEOUT_EVOSUITE=30
GLOBAL_TIMEOUT_RANDOOP=300
DIR_SF_110="${HOME}/Downloads/SF110-20130704"

(cd $DIR_SF_110;
    ls .> projects.txt | sed -i /classes.txt/d projects.txt | sed -i /lib/d projects.txt
)

PROJECTS=$DIR_SF_110/projects.txt

while IFS= read -r project
do
    seeds=( 221 332 121 )
    for seed in "${seeds[@]}"
    do
        ./run-all-approaches.sh ${DIR_SF_110} ${project} $LOCAL_TIMEOUT_EVOSUITE $GLOBAL_TIMEOUT_RANDOOP $seed " "
    done
done < "${PROJECTS}"
