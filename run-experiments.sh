#!/bin/bash

LOCAL_TIMEOUT_EVOSUITE=1
GLOBAL_TIMEOUT_RANDOOP=10

## 10 different seeds 
#seeds=( 432432 5465 1 9 -234932 32 4554 1432 34343 1556 )
seeds=( 432432 )
for seed in "${seeds[@]}"
do
    ./run-tullibee.sh $seed $LOCAL_TIMEOUT_EVOSUITE $GLOBAL_TIMEOUT_RANDOOP
done
