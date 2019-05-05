Continuous Integration:

Continuous Deployment:

Continuous Delivary:


Download jenkins .war file from: https://jenkins.io/download/

run: java -jar jenkins.war --> in the location where jenkins.war is present.
http://localhost:8080

Github jenkins plugnin: Github Integration Plugin

Perform Global Configurations: You can set local paths to GIT, Maven, JDK or install them using jenkins.

Creating Jenkins Maven Job:
--------------------------
Source COde Management: Git,
          URL: Git UL that can be cloned
          Select Branch: By default Master
          Additional Behaviours:  Clean before checkout
                                  Checkout to a subdirectory: Specify the directory name in which this code should be cloned.
Build Triggers:
  Poll SCM: To check if any new commits happed to git, if new commpit happened it will run the job else it will give us the build number in               which a job is ran on this code.
      check the poll SCM.
      Schedule the Poll using Corn job scheduler:
        Syntax: Minutes Hours DayofMonth MonthOfYear DayofWeek(Sunday=0)
        Ex: Poll every 1 minute.=> * * * * *
            Poll every 15 mins =>*/15 * * * *
            Poll every Day => 0 0 * * *
            Poll every three days => 0 0 */3 * *

Build:
  Invoke top level maven targets: Used to execute maven pom.xml
      Maven Version: Local version (used for test basic job, remolty can also be selected)
      Goals: command used to run maven build can be given here.
        Ex: package exec:java, clean install, clean test etc..,
        Advances:
                POM: Give path to  POM.xml
                  Ex: $workspace\TestProject\pom.xml
                    $workspace- gives the directory in which the repository is cloned.
                    \TestProject\pom.xml - path to pom.xml from the repository structure
                      
