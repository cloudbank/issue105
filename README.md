# Technologies Used
I tried to use most recent versions of all the softwares/ technologies at present:

Backend:
- Java 1.8.91
- Maven 3.3.9
- Tomcat 8.0.36
- MySQL 5.7.13
- SonarCube 5.6
- SonarScanner 2.6.1
- EclipseNeon or SpringToolSuite 3.8 (Optional for development)
Following Dependencies are inbuild in package.json, hence no need to install separately:
- Spring 4.3.1
- JUnit 4.12
- Log4j 1.2

# Backend app usage

- Make sure you have JDK 8 installed and JAVA_HOME is set as path where jdk is installed
- Install latest version of MySQL, I am using 5.7.13 and create database schema by running src/main/sql/ddl.sql
-	Either load test data by running src/main/sql/test.sql or create test data using postman(any version v1 or v2) collection in docs folder after running the apis
- Swagger API JSON is also provided
- To start the api run following command in the root folder:
	
	mvn clean
	mvn spring-boot:run
	
- To run unit tests, use following command:

	mvn test
	
-	To build war to deploy to any other application server (not required to run with spring boot which has inbuilt application server):

	mvn install

- Unit tests are run by default, please use -DskipTests parameter everywhere to skip unit tests, e.g.:

	mvn install -DskipTests

# SonarCube Code Coverage for backend

- To use SonarCube code coverage, sonar-project.properties is present in the root of this folder.
- Go to this link and download latest version of SonarCube Server and SonarCube Scanner:
http://www.sonarqube.org/downloads/
http://docs.sonarqube.org/display/SCAN/Analyzing+with+SonarQube+Scanner

SonarCuber Server Direct link:
https://sonarsource.bintray.com/Distribution/sonarqube/sonarqube-5.6.zip

SonarCube Scanner Direct link:
https://sonarsource.bintray.com/Distribution/sonar-scanner-cli/sonar-scanner-2.6.1.zip

I am following this very simple official guide:
http://docs.sonarqube.org/display/SONAR/Get+Started+in+Two+Minutes

- Now unzip both SonarCube Server & SonarCube Scanner (I prefer at root of C:\ but you can choose other).

- Go to SonarCube Server bin directory where executables are present (select directory inside depending on your operating system).
E.g. C:\sonarqube-5.6\bin\windows-x86-64

- Now start the SonarCube Server using StartSonar command or directly click the executable file. You can access the SonarCube dashboard at http://localhost:9000
This is where we will see our report.

- Now open command prompt or terminal and go to the root directory of this project where sonar-project.properties file is present. Run the SonarCube Scanner in the same directory.
E.g. C:\workspace\secyield>c:\sonar-scanner-2.6.1\bin\sonar-scanner

SonarCube will scan our project and details will be populated in the SonarCube dashboard we have at http://localhost:9000

A screenshot is shown in verification guide.