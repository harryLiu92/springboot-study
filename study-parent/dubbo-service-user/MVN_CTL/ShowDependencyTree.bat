@echo off
setlocal
cd /D %0\..\..
call mvn org.apache.maven.plugins:maven-dependency-plugin:2.8:tree -f pom.xml -Dverbose=true -DoutputFile=dependency.tree.txt
pause
