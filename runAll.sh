!/bin/sh

EVALUATION_FUNCTION=$1

clear
<<<<<<< HEAD
javac -cp contest.jar *.java
jar cmf MainClass.txt submission.jar player39.class Individual.class Island.class Population.class Algorithm.class
java -jar testrun.jar -submission=player39 -evaluation=SphereEvaluation -seed=1 –numIslands=10

=======
javac -cp contest.jar player39.java
jar cmf MainClass.txt submission.jar player39.class
java -jar testrun.jar -submission=player39 -evaluation=$EVALUATION_FUNCTION -seed=1
>>>>>>> 2eee629b8b2019b1653f1ca6ba29ae5e8139278c
