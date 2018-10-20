#!/bin/sh
EVALUATION_FUNCTION=$1
javac -cp contest.jar $(find . -name "*.java")
jar cmf MainClass.txt submission.jar $(find . -name "*.class")

COUNTER=0
while [  $COUNTER -lt 10 ]; do
# > txt/schaffers_10_$COUNTER.txt
java -DNumIslands=7 -DPopSize=12 -DMIG_POP=$COUNTER -DBenchmark=false -jar testrun.jar -submission=player39 -evaluation=SchaffersEvaluation -seed=1
let COUNTER=COUNTER+1
done

COUNTER = 0
while [ $COUNTER -lt 10 ]; do
# > txt/schaffers_2_$COUNTER.txt
java -DNumIslands=10 -DPopSize=16 -DMIG_POP=5 -DBenchmark=false -jar testrun.jar -submission=player39 -evaluation=SchaffersEvaluation -seed=1
let COUNTER=COUNTER+1
done
