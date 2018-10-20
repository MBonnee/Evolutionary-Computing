!/bin/sh
EVALUATION_FUNCTION=$1
javac -cp contest.jar $(find . -name "*.java")
jar cmf MainClass.txt submission.jar $(find . -name "*.class")

# GRID SEARCH
MIG_POP_COUNTER=0
while [  $MIG_POP_COUNTER -lt 31 ]; do

COUNTER=0
while [  $COUNTER -lt 10 ]; do
java -DNumIslands=20 -DPopSize=20 -DMIG_POP=$MIG_POP_COUNTER -DBenchmark=false -jar testrun.jar -submission=player39 -evaluation=SchaffersEvaluation -seed=1 > txt/x_${MIG_POP_COUNTER}_ladder_score_${COUNTER}.txt
let COUNTER=COUNTER+1
done

COUNTER=0
while [  $COUNTER -lt 10 ]; do
java -DNumIslands=20 -DPopSize=20 -DMIG_POP=$MIG_POP_COUNTER -DBenchmark=true -jar testrun.jar -submission=player39 -evaluation=SchaffersEvaluation -seed=1 > txt/x_${MIG_POP_COUNTER}_benchmark_score_${COUNTER}.txt
let COUNTER=COUNTER+1
done

let MIG_POP_COUNTER=MIG_POP_COUNTER+3

done

