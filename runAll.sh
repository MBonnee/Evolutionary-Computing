!/bin/bash
clear
javac -cp contest.jar player49.java
jar cmf MainClass.txt submission.jar player49.class
java -jar testrun.jar -submission=player49 -evaluation=SphereEvaluation -seed=1

