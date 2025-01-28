@echo off

call mvn clean package

REM Copy files for Tomcat Docker container
copy .\target\rest-spring6-0.0.2-SNAPSHOT.war .\docker\tomcat\rest-spring.war
copy .\target\rest-spring6-0.0.2-SNAPSHOT.war .\embedded_tomcat\rest-spring.war
