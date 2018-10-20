#!/bin/sh
EVALUATION_FUNCTION=$1
javac -cp contest.jar $(find . -name "*.java")
jar cmf MainClass.txt submission.jar $(find . -name "*.class")

# GRID SEARCH
COUNTER=0
while [  $COUNTER -lt 101 ]; do
# java -DNumIslands=10 -DPopSize=20 -DMIG_POP=3 -DBenchmark=false -DUseLadder=false -jar testrun.jar -submission=player39 -evaluation=KatsuuraEvaluation -seed=1 > output_kat.txt
java -DNumIslands=5 -DPopSize=16 -DMIG_POP=3 -DBenchmark=false -DUseLadder=false -jar testrun.jar -submission=player39 -evaluation=BentCigarFunction -seed=1 > monte/output_bent_${COUNTER}.txt
java -DNumIslands=5 -DPopSize=16 -DMIG_POP=3 -DBenchmark=true -DUseLadder=false -jar testrun.jar -submission=player39 -evaluation=BentCigarFunction -seed=1 > monte/output_bent_bench_${COUNTER}.txt
java -DNumIslands=5 -DPopSize=16 -DMIG_POP=3 -DBenchmark=false -DUseLadder=true -jar testrun.jar -submission=player39 -evaluation=BentCigarFunction -seed=1 > monte/output_bent_ladder_${COUNTER}.txt

java -DNumIslands=10 -DPopSize=20 -DMIG_POP=3 -DBenchmark=false -DUseLadder=false -jar testrun.jar -submission=player39 -evaluation=SchaffersEvaluation -seed=1 > monte/output_schaf_${COUNTER}.txt
java -DNumIslands=10 -DPopSize=20 -DMIG_POP=3 -DBenchmark=true -DUseLadder=false -jar testrun.jar -submission=player39 -evaluation=SchaffersEvaluation -seed=1 > monte/output_schaf_bench_${COUNTER}.txt
java -DNumIslands=10 -DPopSize=20 -DMIG_POP=3 -DBenchmark=false -DUseLadder=true -jar testrun.jar -submission=player39 -evaluation=SchaffersEvaluation -seed=1 > monte/output_schaf_ladder_${COUNTER}.txt
let COUNTER=COUNTER+1
done;
