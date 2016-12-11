#!/bin/bas -lrt
echo `git log -1 --format="%H"` > /home/gitlab-runner/b.build.version
isBuildVerDiff=`diff /home/gitlab-runner/b.build.version  /home/gitlab-runner/b.build.version.prev`
forceBuild=`echo $forceBuild`
echo "current commit verion"
cat /home/gitlab-runner/b.build.version
echo "previous commit version"
echo "$forceBuild"
echo "$isBuildVerDiff"
if [ -z "$isBuildVerDiff" -a "$isBuildVerDiff" != " "  ] && [ -e /home/gitlab-runner/b.build.version.prev ]

 then

 echo "no changes found in branch";

 else

 echo "initializing the build";
 mvn install:install-file -Dfile=customerapi/lib/ojdbc6.jar -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0 -Dpackaging=jar
 mvn -f seccommons/pom.xml clean install
 mvn -f customerapi/pom.xml clean install
 mvn -f secyieldapi/pom.xml clean install
 curl "http://tomcat:admin@localhost:8080/manager/text/undeploy?path=/customerapi"
 curl "http://tomcat:admin@localhost:8080/manager/text/undeploy?path=/secyield"
 curl -T "secyieldapi/target/secyield.war" "http://tomcat:admin@localhost:8080/manager/text/deploy?path=/secyield&update=true"
 curl -T "customerapi/target/customerapi.war" "http://tomcat:admin@localhost:8080/manager/text/deploy?path=/customerapi&update=true"
 echo `git log -1 --format="%H"` > /home/gitlab-runner/b.build.version.prev
 fi
