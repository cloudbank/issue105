# Technologies Used

Backend:
- Java 1.8.91
- Maven 3.3.9
- Tomcat 8.0.36
- MySQL
- SonarCube 5.6
- SonarScanner 2.6.1
- EclipseNeon or SpringToolSuite 3.8 (Optional for development)
Following Dependencies are in package.json, hence no need to install separately:
- Spring 4.3.1
- JUnit 4.12
- Log4j 1.2

# Video
https://www.youtube.com/watch?v=-vxsPC0mS64

# Verification document
docs/SEC Yield Customer API Code Challenge.pdf

# POSTMAN Script
docs/sec_yield_api_postman_collection.json


# Application setup

- Make sure you have JDK 8 installed and JAVA_HOME is set as path where jdk is installed

- Configure the db credentials in  
`customerapi/src/main/resources/db.properties`   `customerapi/src/main/resources/applicationContext-test.xml`.

- Connect to MySQL  
	`mysql -u root -p ` (enter password)

- Create “secyield” and “test-secyield” dbs if they doesn't exist

	```CREATE SCHEMA `secyield` ```  
	```CREATE SCHEMA `test-secyield` ```


- In `seccommons` folder, run the following command

	```mvn clean install ```

- In `customerapi` folder, run the following commands

	1. ```mvn clean install ```   

	1. Import data from excel  
    `mvn exec:java -Dexec.mainClass=com.csa.apex.secyield.ImportExcel -Dexec.args="--excel <path to test>\Phase1TestData.xlsx"`

	  1. You can clean up calculation table by passing --clean or -c options  
	```mvn exec:java -Dexec.mainClass=com.csa.apex.secyield.ImportExcel -Dexec.args=" --clean --excel <path to test>\Phase1TestData.xlsx"```

	  1. You can also pass path of mapping properties for excel if excel will change order of columns  
		```mvn exec:java -Dexec.mainClass=com.csa.apex.secyield.ImportExcel -Dexec.args="--clean --excel <path to test>/Phase1TestData.xlsx --mapping absolute path of customerapi\src\main\resources\excelMapping.properties"```  

	1. ```mvn spring-boot:run ```  

- In MySQL:
 - Switch to “secyield” db  
   ```use secyield ```  

 - Populate the db with test data  
  ```source <path_to_test_data> ``` (where <path_to_test_data> is the absolute path to test_data/test_data.sql)

- In `secyieldapi` folder, run the following commands  

	```mvn clean install ```

	```mvn spring-boot:run ```


To run unit tests, use the following command in `seccommons`, `customerapi` and `secyieldapi` folders (note that the Customer API has to be running when executing Public API tests):

	```mvn test```

Unit tests are run by default, please use -DskipTests parameter everywhere to skip unit tests, e.g.:

	```mvn install -DskipTests```

# SonarCube Code Coverage for backend

- To use SonarCube code coverage, sonar-project.properties is present in the root folders of seccommons, customerapi and secyieldapi modules.
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

Also one can use  

```mvn sonar:sonar```
