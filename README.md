# Technologies Used

Backend:
- Java 1.8.91
- Maven 3.3.9
- Tomcat 8.0.36
- Oracle Express 11g
- Oracle Developer
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

# Oracle Express 11g Installation
###### This only covers installation. Configuration will be done separately.
## Windows
1. To install Oracle Express 11g for Windows, follow the guide provided in this link: https://docs.oracle.com/cd/E17781_01/install.112/e18803/toc.htm#XEINW124
2. After installation, by default the database is already started. In case the database is stopped, follow this guide on how to start it: https://docs.oracle.com/cd/E17781_01/install.112/e18803/toc.htm#XEINW133

###### For Windows, the Oracle port we will use to connect is 1521.


## Linux (Red Hat Linux 5)
1. To install Oracle Express 11g for Linux, follow the guide provided in this link: http://docs.oracle.com/cd/E17781_01/install.112/e18802/toc.htm#XEINL122

###### For Linux, the Oracle port we will use to connect is 1521.

## Mac OS X (via Docker)
1. Currently, there is no readily available installation package for Mac. To make the installation simpler, we will use Docker. Download Docker for Mac from this link: https://download.docker.com/mac/stable/Docker.dmg
2. Follow the steps (Step 1 is sufficient) in the link to install Docker: https://docs.docker.com/docker-for-mac/#/step-1-install-and-run-docker-for-mac. 
3. Open a Terminal and execute the following commands:
   - `docker pull wnameless/oracle-xe-11g`
   - `docker run -d -p 49160:22 -p 49161:1521 -e ORACLE_ALLOW_REMOTE=true wnameless/oracle-xe-11g`

###### For Mac OS X, the Oracle port we will use to connect is 49161. The `system` password is `oracle`.


# Oracle Developer Installation

###### We will use this tool for easier execution of database scripts.

1. Download the appropriate install package from this link: http://www.oracle.com/technetwork/developer-tools/sql-developer/downloads/index.html
2. Installation is pretty straight forward,
3. Configure connection to the local Oracle Express DB. We will need to create 3 connections:
   - For `system` user
   > Connection name: local_system
   
   > Username: system
   
   > Password: whatever_you_have_set
   
   > Hostname: localhost
   
   > Port: 1521 or 49161
   
   > SID: xe
 
   - For `secyield` user
   > Connection name: local_secyield
   
   > Username: secyield
   
   > Password: password
   
   > Hostname: localhost
   
   > Port: 1521 or 49161
   
   > SID: xe
 
   - For `test_secyield` user
   > Connection name: local_test_secyield
   
   > Username: test_secyield
   
   > Password: password
   
   > Hostname: localhost
   
   > Port: 1521 or 49161
   
   > SID: xe
 

# Application setup

- Make sure you have JDK 8 installed and JAVA_HOME is set as path where jdk is installed

#### DB Setup
- Configure the db credentials in  
`customerapi/src/main/resources/db.properties`   `customerapi/src/main/resources/applicationContext-test.xml`.

- Open Oracle Developer and connect to `local_system` and run `sql/create_user.sql`. This will create the two users `secyield` and `test_secyield`.

- Still in Oracle Developer, connect to `local_secyield` and run `sql/schema.sql`. This will create the table structure for user `secyield`

- Still in Oracle Developer, connect to `local_test_secyield` and run `sql/schema.sql`. This will create the table structure for user `test_secyield`

- In case you want to pre-load some data, in Oracle Developer, connect to `local_secyield` and run `test_data/clear.sql` & `test_data/test_data.sql`.

#### Build App
- In `seccommons` folder, run the following command

  ```mvn clean install ```

- In `customerapi` folder, run the following commands

  1. `mvn install:install-file -Dfile=lib/ojdbc6.jar -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0 -Dpackaging=jar`
  
  2. ```mvn clean install ```   

  3. Import data from excel  
    `mvn exec:java -Dexec.mainClass=com.csa.apex.secyield.commands.ImportExcel -Dexec.args="--excel <path to test>\Phase1TestData.xlsx"`

  4. You can clean up calculation table by passing --clean or -c options  
  ```mvn exec:java -Dexec.mainClass=com.csa.apex.secyield.commands.ImportExcel -Dexec.args=" --clean --excel <path to test>\Phase1TestData.xlsx"```

  5. You can also pass path of mapping properties for excel if excel will change order of columns  
    ```mvn exec:java -Dexec.mainClass=com.csa.apex.secyield.commands.ImportExcel -Dexec.args="--clean --excel <path to test>/Phase1TestData.xlsx --mapping absolute path of customerapi\src\main\resources\excelMapping.properties"```  

  6. ```mvn spring-boot:run ```  

- In `secyieldapi` folder, run the following commands  

  ```mvn clean install ```

  ```mvn spring-boot:run ```

To run unit tests, use the following command in `seccommons`, `customerapi` and `secyieldapi` folders (note that the Customer API has to be running when executing Public API tests):

    mvn test

Unit tests are run by default, please use -DskipTests parameter everywhere to skip unit tests, e.g.:

  mvn install -DskipTests

# Deployment to Tomcat

- In `seccommons` folder, run the following command
   ```
   mvn clean install
   ```

- In `customerapi` folder, run the following command
   ```
   mvn clean install
   ```
   * Copy generated war (found in `target/customerapi.war`) to Tomcat webapp directory (`TOMCAT_HOME/webapps`).

- In `secyieldapi` folder, update the URLs in `resources/application.tomcat.properties` to point to the correct `customerapi` endpoint
   ```
   getConfigApiPath=http://localhost:8080/customerapi/securitySECDataConfiguration
   getCustomerDataApiPath=http://localhost:8080/customerapi/customerSecuritySECData
   saveCalculatedSecuritySECDataApiPath=http://localhost:8080/customerapi/persistSecuritySECData
   getCalculatedSecuritySECDataApiPath=http://localhost:8080/customerapi/calculatedSecuritySECData
   ```

- In `secyieldapi` folder, run the following command
   ```
   mvn clean install -Ptomcat
   ```
   * Copy generated war (found in `target/secyield.war`) to Tomcat webapp directory (`TOMCAT_HOME/webapps`).

- Start Tomcat server
- Once started, these will be the endpoints:
  * customerapi --  `/customerapi/<service_name>`  [e.g. `/customerapi/securitySECDataConfiguration`]
  * secyieldapi --  `/secyield/<service_name>`  [e.g. `/secyield/calculatedSecuritySECData`]


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
