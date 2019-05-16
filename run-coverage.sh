OUTPUT_DIR=$1
PROJECT=$2
DIR_SF_110=$3

if [[ -z "$OUTPUT_DIR" || -z "$PROJECT"  || -z "$DIR_SF_110"  ]];
then
    echo "missing variable. please check and set before calling!"
    echo OUTPUT_DIR=$OUTPUT_DIR
    echo PROJECT=$PROJECT
    echo DIR_SF_110=$DIR_SF_110
    exit
fi

#########
## Compiling, generating list and reading the tests
#########

## building claspath
PROJECT_JAR=$(echo "$PROJECT" | cut -f2 -d\_)".jar"
PROJECT_CLASSPATH="$DIR_SF_110/${PROJECT}/$PROJECT_JAR"
for x in `find $DIR_SF_110/${PROJECT}/lib -name "*.jar"`;
do
    PROJECT_CLASSPATH=$x:${PROJECT_CLASSPATH}
done

## this directory
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
RANDOOP_JAR=${DIR}/libs/randoop/randoop-all-4.1.1.jar
EVOSUITE_JAR=${DIR}/libs/evosuite/evosuite-1.0.6.jar

## defining some important paths
JACOCO_DIR=${DIR}/libs/jacoco
JACOCO_AGENT=${JACOCO_DIR}/jacocoagent.jar
JACOCO_CLI=${JACOCO_DIR}/jacococli.jar
JUNIT_JARS=${DIR}/libs/junit/hamcrest-core-1.3.jar:${DIR}/libs/junit/junit-4.13-beta-2.jar

cd ${OUTPUT_DIR};

## compile all tests
CP=${PROJECT_CLASSPATH}:${JUNIT_JARS}:${EVOSUITE_JAR}
find . -name "*.java" | xargs javac -cp $CP -d .

## executing tests as to generate jacoco.exec files
tmp=$(basename $OUTPUT_DIR)
if [[ $tmp == randoop+evosuite* ]]
then
    echo "Running Randoop + EvoSuite tests..."
    ###################################
    ## running Randoop + EvoSuite tests
    ###################################

    ## running Randoop tests
    java -cp .:$CP -javaagent:${JACOCO_AGENT} org.junit.runner.JUnitCore synapse.RegressionTest
    mv jacoco.exec jacoco.exec.$file

    ## running Evosuite tests
    EVOTESTS="${DIR}/list-of-classes-EVO.txt"          
    find . -name "*.class" | sed 's/\.class//g' | sed 's/\.\///g' | sed 's/\//./g' > ${EVOTESTS}
    ## process file
    while IFS= read -r file
    do
	    echo "Reading... $file"
	    java -cp .:$CP -javaagent:${JACOCO_AGENT} org.junit.runner.JUnitCore $file
        mv jacoco.exec jacoco.exec.$file
    done < "$EVOTESTS"
    rm $EVOTESTS

    ## merge jacoco.exec files into one and delete the rest
    covfiles=$(find . -name "jacoco.exec*" | xargs)
    java -jar $JACOCO_CLI merge $covfiles --destfile jacoco.exec
    rm $covfiles

elif [[ $tmp == randoop* ]];
then
    echo "Running Randoop tests..."
    #########################
    ## running Randoop tests
    #########################
    java -cp .:$CP -javaagent:${JACOCO_AGENT} org.junit.runner.JUnitCore synapse.RegressionTest

else
    echo "Running EvoSuite tests..."
    #########################
    ## running EvoSuite tests
    #########################    

    EVOTESTS="${DIR}/list-of-classes-EVO.txt"          
    find . -name "*.class" | sed 's/\.class//g' | sed 's/\.\///g' | sed 's/\//./g' > ${EVOTESTS}
    ## process file
    while IFS= read -r file
    do
	     echo "Reading... $file"
	     java -cp .:$CP -javaagent:${JACOCO_AGENT} org.junit.runner.JUnitCore $file
        mv jacoco.exec jacoco.exec.$file
    done < "$EVOTESTS"
    rm $EVOTESTS
    
    ## merge jacoco.exec files into one and delete the rest
    covfiles=$(find . -name "jacoco.exec*" | xargs)
    java -jar $JACOCO_CLI merge $covfiles --destfile jacoco.exec
    rm $covfiles
fi


java -jar $JACOCO_CLI report jacoco.exec \
     --classfiles ${PROJECT_CLASSPATH} \
     --csv jacoco.csv
rm jacoco.exec   

