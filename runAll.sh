!/bin/bash
clear
javac -cp contest.jar player39.java
jar cmf MainClass.txt submission.jar player39.class
java -jar testrun.jar -submission=player39 -evaluation=SphereEvaluation -seed=1

