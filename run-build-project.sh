DIR_SF_110=$1
PROJECT=$2

if [[ -z "$PROJECT" || -z "DIR_SF_110" ]];

then
    echo "missing variable. please check and set before calling!"
    echo DIR_SF_110=$DIR_SF_110
    echo PROJECT=$PROJECT
    exit
fi

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
EVOSUITE_JAR=${DIR}/libs/evosuite/evosuite-1.0.6.jar

(

 ant compile

 PROJECT_CLASSES=`pwd`/build/classes
 SEED=124321
 LOCAL_TIMEOUT_EVOSUITE=1
 
 java -jar ${EVOSUITE_JAR} -generateSuite \
      -target=${PROJECT_CLASSES} \
      -seed=${SEED} \
      -criterion=branch \
      -base_dir=/tmp/ \
   	-Dsearch_budget=${LOCAL_TIMEOUT_EVOSUITE} \
	   -Dstopping_condition=MaxTime \
      -Dno_runtime_dependency=true

)
