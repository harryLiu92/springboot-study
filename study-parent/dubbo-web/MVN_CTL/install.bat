@echo off
setlocal
cd /D %0\..\..

call mvn  clean install -Dmaven.test.skip=true -f pom.xml

pause