!/bin/bash
clear
javac -cp contest.jar *.java
jar cmf MainClass.txt submission.jar player39.class Individual.class Island.class Population.class Algorithm.class
java -jar testrun.jar -submission=player39 -evaluation=SphereEvaluation -seed=1 –numIslands=10

