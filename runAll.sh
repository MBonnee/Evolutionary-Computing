!/bin/sh
EVALUATION_FUNCTION=$1
javac -cp contest.jar $(find . -name "*.java")
jar cmf MainClass.txt submission.jar $(find . -name "*.class")
java -DNumIslands=1 -DPopSize=100 -jar testrun.jar -submission=player39 -evaluation=SchaffersEvaluation -seed=1




