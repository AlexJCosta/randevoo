#!/bin/bash

PROJECT=$1

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

function analyze() {
    ALGO=$1
    ## last execution of this algo
    LASTRUN=$(ls -d -t ${DIR}/output-tests/${ALGO}-${PROJECT}* | grep -vE "\-sub" | head -n 1)
    (cd $LASTRUN;
     #grep -f files_to_test.txt jacoco.csv  | awk -F "," '{print $2"."$3,$6,$7}' 
     grep -f files_to_test.txt jacoco.csv  | awk -F "," 'BEGIN {SUM1=0;SUM2=0}; {SUM1=SUM1+$6;SUM2=SUM2+$7}; END {printf "uncovered=%.3f covered=%.3f coverage=%.3f\n", SUM1, SUM2, 100*SUM2/(SUM1+SUM2)}'
    )
    
}

echo "RANDOOP"
analyze "randoop"

echo "EVOSUITE"
analyze "evosuite"

echo "RANDOOP AFTER EVOSUITE"
analyze "randoop_after_evosuite"

# echo "RANDOOP + EVOSUITE"
# analyze "randoop+evosuite"

# echo "EVOSUITE FROM RANDOOP"
# analyze "evosuite-from-evosuite"
