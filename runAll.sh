!/bin/sh
EVALUATION_FUNCTION=$1
javac -cp contest.jar $(find . -name "*.java")
jar cmf MainClass.txt submission.jar $(find . -name "*.class")
java -DNumIslands=2 -DPopSize=12 -DBenchmark=false -jar testrun.jar -submission=player39 -evaluation=BentCigarFunction -seed=1 > output.txt
