#!/bin/bash
echo `git log -1 --format="%H"` > /home/gitlab-runner/f.build.version
isBuildVerDiff=`diff /home/gitlab-runner/f.build.version  /home/gitlab-runner/f.build.version.prev`
forceBuild=`echo $forceBuild`
echo "current commit verion"
cat /home/gitlab-runner/f.build.version
echo "previous commit version"
echo "$forceBuild"
echo "$isBuildVerDiff"
if [ -z "$isBuildVerDiff" -a "$isBuildVerDiff" != " "  ] && [ -e /home/gitlab-runner/f.build.version.prev ]

 then

 echo "no changes found in branch";

 else

 echo "initializing the build";
 npm install
 npm run build.prod
 cp -R dist/prod/. /var/www/html/
 echo `git log -1 --format="%H"` > /home/gitlab-runner/f.build.version.prev
 fi
