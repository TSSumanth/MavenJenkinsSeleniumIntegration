# MavenJenkinsSeleniumIntegration

Maven is a build automation tool used primarily for Java projects.

GroupId: A group ID is a universally unique identifier for a project. While this is often just the project name (eg. commons-collections), it is helpful to use a fully-qualified package name to distinguish it from other projects with a similar name (eg. org.apache.maven).



Steps:
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

6. mvn package exec:java
	<build>
		<plugins>
			<plugin>
				  	<groupId>org.codehaus.mojo</groupId>
  					<artifactId>exec-maven-plugin</artifactId>
  					<version>1.4.0</version>
  					<configuration>
  						<mainClass>${project.basedir}\Selenium.test.sumanthmavern</mainClass>
  					</configuration>
  			</plugin>
		</plugins>
	</build>

7. 
