#!/bin/bash

#WGET installation
sudo yum -y install wget

#Git CI Runner installation
curl -L https://packages.gitlab.com/install/repositories/runner/gitlab-ci-multi-runner/script.rpm.sh | sudo bash
sudo yum -y  install gitlab-ci-multi-runner
touch /home/gitlab-runner/f.build.version.prev
touch /home/gitlab-runner/f.build.version
touch /home/gitlab-runner/b.build.version.prev
touch /home/gitlab-runner/b.build.version

#Java Installation
wget --no-cookies --no-check-certificate --header "Cookie: gpw_e24=http%3A%2F%2Fwww.oracle.com%2F; oraclelicense=accept-securebackup-cookie" "http://download.oracle.com/otn-pub/java/jdk/8u111-b14/jdk-8u111-linux-x64.rpm"

sudo yum -y localinstall jdk-8u111-linux-x64.rpm

#Maven Installation
sudo wget http://www-us.apache.org/dist/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.tar.gz
sudo tar -xvf apache-maven-3.3.9-bin.tar.gz
sudo mv apache-maven-3.3.9  /usr/local/apache-maven
sudo echo ' export M2_HOME=/usr/local/apache-maven;export M2=$M2_HOME/bin;export PATH=$M2:$PATH;' > maven.sh
sudo mv maven.sh  /etc/profile.d/maven.sh


#NodeJS Installation
wget --no-check-certificate  https://nodejs.org/dist/v6.9.2/node-v6.9.2-linux-x64.tar.xz
sudo mkdir /usr/local/node
sudo tar --strip-components 1 -xJf node-v6.9.2-linux-x64.tar.xz -C /usr/local/node
sudo echo  'export NODE_HOME=/usr/local/node;export NODE_BIN=$NODE_HOME/bin;export PATH=$NODE_BIN:$PATH'  >   node.sh
sudo mv node.sh /etc/profile.d/node.sh


#Tomcat Installation
sudo groupadd webserver
sudo useradd -M -s /bin/nologin -g webserver -d /opt/tomcat webserver
wget http://www-eu.apache.org/dist/tomcat/tomcat-8/v8.5.8/bin/apache-tomcat-8.5.8.tar.gz
sudo mkdir /opt/tomcat
sudo tar xvf apache-tomcat-8*tar.gz -C /opt/tomcat --strip-components=1
sudo chown -R webserver /opt/tomcat/

#Apache Installation
sudo yum -y install httpd


sudo usermod -a -G webserver gitlab-runner
sudo chown -R webserver:webserver /var/www/html
sudo chown -R webserver:webserver /opt/tomcat
sudo chmod 774 -R    /opt/tomcat
sudo chmod 774 -R  /var/www/html
sudo usermod -a -G gitlab-runner apache
sudo usermod -a -G webserver apache

exec bash