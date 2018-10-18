!/bin/sh
EVALUATION_FUNCTION=$1
clear
javac -cp contest.jar *.java
jar cmf MainClass.txt submission.jar player39.class Individual.class Island.class SortByFitness.class Population.class Algorithm.class
java -DNumIslands=10 -DPopSize=100 -jar testrun.jar -submission=player39 -evaluation=BentCigarFunction -seed=1
