#!/bin/sh
EVALUATION_FUNCTION=$1
javac -cp contest.jar $(find . -name "*.java")
jar cmf MainClass.txt submission.jar $(find . -name "*.class")

java -DNumIslands=5 -DPopSize=16 -DMIG_POP=3 -DBenchmark=false -DUseLadder=false -jar testrun.jar -submission=player39 -evaluation=BentCigarFunction -seed=1 > output_bent.txt
java -DNumIslands=5 -DPopSize=16 -DMIG_POP=3 -DBenchmark=true -DUseLadder=false -jar testrun.jar -submission=player39 -evaluation=BentCigarFunction -seed=1 > output_bent_bench.txt
java -DNumIslands=5 -DPopSize=16 -DMIG_POP=3 -DBenchmark=false -DUseLadder=true -jar testrun.jar -submission=player39 -evaluation=BentCigarFunction -seed=1 > output_bent_ladder.txt

java -DNumIslands=10 -DPopSize=20 -DMIG_POP=3 -DBenchmark=false -DUseLadder=false -jar testrun.jar -submission=player39 -evaluation=SchaffersEvaluation -seed=1 > output_schaf.txt
java -DNumIslands=10 -DPopSize=20 -DMIG_POP=3 -DBenchmark=true -DUseLadder=false -jar testrun.jar -submission=player39 -evaluation=SchaffersEvaluation -seed=1 > output_schaf_bench.txt
java -DNumIslands=10 -DPopSize=20 -DMIG_POP=3 -DBenchmark=false -DUseLadder=true -jar testrun.jar -submission=player39 -evaluation=SchaffersEvaluation -seed=1 > output_schaf_ladder.txt
