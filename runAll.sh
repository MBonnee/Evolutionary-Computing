!/bin/bash

EVALUATION_FUNCTION=$1

clear
javac -cp contest.jar player39.java
jar cmf MainClass.txt submission.jar player39.class
java -jar testrun.jar -submission=player39 -evaluation=$EVALUATION_FUNCTION -seed=1
