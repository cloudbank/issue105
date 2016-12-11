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


# Deployment to AWS 

#### AWS Instance Creation
- As a prerequisite , you should have AWS account to create instance on AWS
- Login to AWS and select services ->  EC2 as shown below

![](docs/img/login_service.png )

- Click on Launch Instance button to create new instance

![](docs/img/launch_instance.png )

- Select Red Hat Enterprise Linux 7.3 instance

![](docs/img/select_red_hat.png )

- On Choose an Instance Type page click Next as default settings is fine 
- On Configure Instance Details page click Next as default settings is fine 
- On Add Storage page , click on Add New Volume to add 4GB space and click Next, which will be used as SWAP memory 

![](docs/img/add_storage.png )

- Click Next on  Add Tags page
- On Configure Security Group page , add required inbound ports by clicking AddRule , all below ports(80,8080,1521,22) 
should  be added with 	Source as 0.0.0.0/0 and click Review And Launch 

![](docs/img/security_grp.png )

- Once you verify all the details click on Launch

![](docs/img/review.png )

- Once you click on Launch , you will be promoted to select security key pair , if you dont have any key pair already , click on Download Key Pair 
after naming it. This will be used to login into the server.

![](docs/img/keypair.png )

- Once you downloaded key pair click on Launch and View Instance
- Now you can see newly created instance, We need one more instance for Oracle DB
- Right click on Intance and select Launch More like this option to clone this instance

![](docs/img/clone_instance.png )

-Review the cloned instance and Lunch

![](docs/img/clone_review.png )


#### Installtion on AWS instance
- Login into any one of the created instance  through putty
- Please refer the link for getting ppk file from pem file and using that to login 
 https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/putty.html?icmpid=docs_ec2_console
- Default user name would be ec2-user
- Once you login, execute the script file script/install_sec.sh , this will install apache , tomcat , java , maven and node.
you can edit urls and names in script to install different version if needed. you can copy the file using winscp or create new file using  ```vi install_sec.sh```
and copy paste the content of the file
        ```sudo sh install_sec.sh```
- Now we have to configure tomcat as a service.
- Create and open the Systemd  file by below command and copy paste content from script/tomcat.service
```sudo vi /etc/systemd/system/tomcat.service```
- Reload Systemd  to load tomcat file
```sudo systemctl daemon-reload```
- Start tomcat using below command
```sudo systemctl start tomcat```
- Make tomcat as a service by below command
```sudo systemctl enable tomcat```

- Start apache httpd server using below command
```sudo service  httpd start```

#### GIT CI Runner configuration
-Before we begin git ci runner configuration , we have to get runner token from the gitlab
- To get runner token , browse the project in gitlab and click on setting icon on top right corner and select Runner 

![](docs/img/gitrunner.png )

- This page displays runner information and available runners ,you can  "Disable Shared Runners" here

![](docs/img/runnerinfo.png )

- Once you get token , login into web server EC2 instance we created and run below command to register and configure  runner.

```sudo gitlab-ci-multi-runner register``` 

- You will be asked to provide inputs for following questions once you run above command.
    1.Please enter the gitlab-ci coordinator URL (e.g. https://gitlab.com/): https://gitlab.com/ci (obtained from runner page in gitlab)
    2.Please enter the gitlab-ci token for this runner: zmaasJ3Ynq3RXxikwGov (obtained from runner page in gitlab)
    3.Please enter the gitlab-ci description for this runner: backendrunner (runner description here)
    4.Please enter the gitlab-ci tags for this runner (comma separated): backend, java (tags here)
    5.Please enter the executor: docker-ssh, ssh, virtualbox, docker-ssh+machine, kubernetes, docker, parallels, shell, docker+machine: shell (we will be using shell runner)
 
 - Runner has been  registered and will be visible in gitlab runner configuration page of the project
 - Create one more runner for front end build based on token from frontend project runner cofig page
 - We have to create build scripts in gitlab-runner's home directory , our gitlab-ci.yml file is based on these scripts
 - Our logged in user is ec2-user , switch to gitlab-runner user and create two build file backend_build.sh and frontend_build.sh 
 - user below command to switch to gitlab-runner user and go to home directory to create file in home directory.
 ```sudo su gitlab-runner``` 
 ```cd ~```
 - These two files are already available with this project in scripts/ directory. you can copy copy the content and paste in server using vi editor
 - Gitlab CI triggers the build based on commits to entire repository , we are restricting the build using commit version of required branch.
 - If commit version of previous and current build requests are same then we are not doing the build
 - Below four file are required to maintain the commit version , make sure they are available in gitlab-runner home directory , they are created as a part of install script,
 if they are not available , create them using below commands.
 ```
 touch /home/gitlab-runner/f.build.version.prev
 touch /home/gitlab-runner/f.build.version
 touch /home/gitlab-runner/b.build.version.prev
 touch /home/gitlab-runner/b.build.version
 ``` 
 
#### Finalizing AWS webserver setup.

- We have configured apache and tomcat webservers requied to host our frontend and backend.
- Make sure our server are up and running , for testing apache hit http://ipaddress/ (ex:http://35.165.104.194/) , for tomcat http://ipaddress:8080/ (ex:http://35.165.104.194:8080/)
- Take a note of webserver ip address , we will using this in our application configuration file.
 
#### Oracle XE Configuration on AWS
- Login into next instance of EC2 using putty. This instance will be used as our DB server.
- We need oracle rpm package for installation , it can not be downloaded from server since oracle website required you to login to download.
- You can download package from official site and copy the rpm package through WinSCP.
Download link http://www.oracle.com/technetwork/database/database-technologies/express-edition/downloads/index.html
- Here our downloaded file name is oracle-xe-11.2.0-1.0.x86_64.rpm.zip and you can run below commands to install oracle xe

```
sudo yum -y install unzip
sudo yum -y install libaio
sudo yum -y install bc
sudo unzip "oracle-xe-11.2.0-1.0.x86_64.rpm.zip"
cd Disk1
sudo rpm -i oracle-xe-11.2.0-1.0.x86_64.rpm
```

- Once oracle-xe is installed , you can configure using below command.

```sudo /etc/init.d/oracle-xe configure```

- it will be asking for http port , oracle listenr port , password for sys, system and load on start up values 
- you can leave default for http(8080) , oracle listener port (1521) and select y for start on system boot option.

### Updating application configurations

######Updating backend project properties
- update below files with new webserver ip address /port number and DB server ip  address and port number

secyieldapi\src\main\resources\application.properties
customerapi\src\main\resources\db.properties
customerapi\src\main\resources\applicationContext-test.xml

######Updating frontend project properties
- Update below file with backend api details
src\client\app\shared\config\app-config.ts

### Triggering build.
- When you commit any changes in develop branch of frontend / backend project , build will be triggered automatically.
- You can monitor the build status from gitlab , Pipeline page.
- If any test cases fails or build fails  , maven will return non zero retrun code , which will stop our build process from proceeding  . 
- After the build our app will be available at webserver at http://ipaddress/ (ex:http://35.165.104.194/)





