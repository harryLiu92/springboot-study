@echo off
setlocal
cd /D %0\..\..
call mvn org.apache.maven.plugins:maven-eclipse-plugin:2.8:clean
call mvn org.apache.maven.plugins:maven-eclipse-plugin:2.8:eclipse -DdownloadSources=true
pause
