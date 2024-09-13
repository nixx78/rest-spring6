@echo off

set EXCHANGE_TARGET=.\docker\_exchange\

del /q %EXCHANGE_TARGET%
md %EXCHANGE_TARGET%
copy .\exchange\ %EXCHANGE_TARGET%

call mvn clean package

REM Copy files for Tomcat Docker container
copy .\target\rest-spring6-0.0.1-SNAPSHOT.war .\docker\tomcat\rest-spring.war

REM Copy files for Java Docker container
copy .\target\rest-spring6-0.0.1-SNAPSHOT.war .\docker\jvm_alpine\rest-spring.war

REM Copy files for Java Docker Composite
copy .\target\rest-spring6-0.0.1-SNAPSHOT.war .\docker\composite\rest-spring.war
