# MavenJenkinsSeleniumIntegration

Maven is a build automation tool used primarily for Java projects.

GroupId: A group ID is a universally unique identifier for a project. While this is often just the project name (eg. commons-collections), it is helpful to use a fully-qualified package name to distinguish it from other projects with a similar name (eg. org.apache.maven).



Steps To Execute a TestNG File:
-------------------------------
1. Create a testNg file.
  	@Test
	public void start() {
		System.out.println("Testng");
	}
2. Create TestNg.xml file.
     <?xml version="1.0" encoding="UTF-8"?>
    <suite name="softwaretestingmaterial">
      <test name="testngTest">
        <classes>
          <class name="test.testngclass2" />
        </classes>
      </test>
    </suite>

3. In POM.xml use this below plugin.
    <build>
		<plugins>
			<plugin>
				  	<groupId>org.apache.maven.plugins</groupId>
  					<artifactId>maven-surefire-plugin</artifactId>
  					<version>3.0.0-M3</version>

				<configuration>
					<suiteXmlFiles>testNg.xml</suiteXmlFiles>
				</configuration>
			</plugin>
		</plugins>
	</build>
  
 4. INstall Maven on windows: https://www.mkyong.com/maven/how-to-install-maven-in-windows/
    Once maven is installed locally, we can run maven through command prompt.
    -mvn clean // to clean the project
    -mvn install // to run the POM.xml
    

5. Send Parameters from maven to TestNg.
  	<build>
		<plugins>
			<plugin>
				  	<groupId>org.apache.maven.plugins</groupId>
  					<artifactId>maven-surefire-plugin</artifactId>
  					<version>3.0.0-M3</version>
				
				<configuration>
					<parallel>all</parallel>
					<threadCount>10</threadCount>
					<suiteXmlFiles>testNg.xml</suiteXmlFiles>
					<systemPropertyVariables>
						<browserdetails>${browser}</browserdetails>
						<ResultsPath>${path}</ResultsPath>
					</systemPropertyVariables>
					<testSourceDirectory>${basedir}/Selenium</testSourceDirectory>
                    <testClassesDirectory>${project.build.directory}/classes/</testClassesDirectory>
				</configuration>
			</plugin>
		</plugins>
	</build>

	=> For executing tests in parallel we set parallel and threadcount tags.
	=> For executing tests in different location than in default location we use testSourceDirectory.

	To send parameters from command prompt => mvn install -Dparameter1=value1 -Dparameter2=value2 ...

	To access these parameters in TestNG use @Parameters and pass these same parameternames as method arguments as shown below:
     	  @Parameters({"browser","path"})
	  @Test
	  public void f(String browser,String path) {
		  System.out.println("First Test NG  Test"+browser+path);
	  }
6. Maven by default compiles .java files in main and test directory and we have to compile the other directories on our own. To do that    we have to add the below lines in pom.xml.
	<build>
		<plugins>
			 <plugin>
			    <groupId>org.codehaus.mojo</groupId>
			    <artifactId>build-helper-maven-plugin</artifactId>
			    <version>3.0.0</version>
			    <executions>
				<execution>
				    <phase>generate-sources</phase>
				    <goals>
					<goal>add-source</goal>
				    </goals>
				    <configuration>
					<sources>
					    <source>Selenium</source>
					</sources>
				    </configuration>
				</execution>
			    </executions>
			</plugin>
		</plugins>
	</build>
	
   here folder in which we have written the source code/ test code, or code that we need to compile explictly should be present in 
   <source>Selenium</source>. //'Selenium' is the folder name
============================================

Execute a Java Main method using maven
--------------------------------------

1. Add the belwo lines in pom.xml. mainClass that we need to run, path details should be specified in 
	<mainClass>test.sumanthmavern</mainClass> //packagename.classname
	
	<build>
		<plugins>
			<plugin>
				  	<groupId>org.codehaus.mojo</groupId>
  					<artifactId>exec-maven-plugin</artifactId>
  					<version>1.4.0</version>
  					<configuration>
  						<mainClass>test.sumanthmavern</mainClass>
  					</configuration>
  			</plugin>
		</plugins>
	</build>

2. mvn package exec:java



========================================


Add Local Jar to maven repository:
---------------------------------
1. Create a folder inside the project and Download the jar required and place it in that folder.
2. Now this jar should be installed to mavens local repository.

	Synatx:
		mvn install:install-file -Dfile=<path-to-file> -DgroupId=<group-id> -DartifactId=<artifact-id> -Dversion=<version>		       -Dpackaging=<packaging> -DgeneratePom=true
		Ex: mvn install:install-file  -Dfile=D:\Selenium\Oracle JDBC Jar\ojdbc8.jar -DgroupId=com.oracle -DartifactId=ojdbc8 			-Dversion=12.2.0.1  -Dpackaging=jar -DgeneratePom=true
	
	Where each refers to:

		< path-to-file >: the path to the file to load e.g -> c:\kaptcha-2.3.jar

		< group-id >: the group that the file should be registered under e.g -> com.google.code

		< artifact-id >: the artifact name for the file e.g -> kaptcha

		< version >: the version of the file e.g -> 2.3

		< packaging >: the packaging of the file e.g. -> jar


=======================================

Default Variables:
------------------
// ${project.basedir} -- gives the project directory
