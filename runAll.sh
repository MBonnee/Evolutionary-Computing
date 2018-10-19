!/bin/sh
EVALUATION_FUNCTION=$1
javac -cp contest.jar $(find . -name "*.java")
jar cmf MainClass.txt submission.jar $(find . -name "*.class")

# GRID SEARCH
COUNTER=0
while [  $COUNTER -lt 10 ]; do
java -DNumIslands=20 -DPopSize=20 -DMIG_POP=3 -DBenchmark=false -jar testrun.jar -submission=player39 -evaluation=SchaffersEvaluation -seed=1 > txt/x_ladder_score_$COUNTER.txt
let COUNTER=COUNTER+1
done

COUNTER=0
while [  $COUNTER -lt 10 ]; do
java -DNumIslands=20 -DPopSize=20 -DMIG_POP=3 -DBenchmark=true -jar testrun.jar -submission=player39 -evaluation=SchaffersEvaluation -seed=1 > txt/x_benchmark_score_$COUNTER.txt
let COUNTER=COUNTER+1
done

