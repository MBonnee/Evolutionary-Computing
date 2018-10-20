!/bin/sh
EVALUATION_FUNCTION=$1
javac -cp contest.jar $(find . -name "*.java")
jar cmf MainClass.txt submission.jar $(find . -name "*.class")


$COUNTER = 0
while [  $COUNTER -lt 10 ]; do
java -DNumIslands=10 -DPopSize=16 -DMIG_POP=5 -DBenchmark=false -jar testrun.jar -submission=player39 -evaluation=SchaffersEvaluation -seed=1 > txt/schaffers_10_$COUNTER.txt
let COUNTER=COUNTER+1
done

$COUNTER = 0

while [  $COUNTER -lt 10 ]; do
java -DNumIslands=2 -DPopSize=16 -DMIG_POP=5 -DBenchmark=false -jar testrun.jar -submission=player39 -evaluation=SchaffersEvaluation -seed=1 > txt/schaffers_2_$COUNTER.txt
let COUNTER=COUNTER+1
done
