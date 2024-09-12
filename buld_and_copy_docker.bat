@echo off
set EXCHANGE_TARGET_TOMCAT=.\docker\tomcat\exchange\

rem call mvn clean package

REM Copy files for Tomcat Docker container

del /q %EXCHANGE_TARGET_TOMCAT%
md %EXCHANGE_TARGET_TOMCAT%

copy .\target\rest-spring6-0.0.1-SNAPSHOT.war .\docker\tomcat\rest-spring.war 
copy .\exchange\ %EXCHANGE_TARGET_TOMCAT%


REM Copy files for Java Docker container

set EXCHANGE_TARGET_JAVA=.\docker\jvm_alpine\exchange\

del /q %EXCHANGE_TARGET_JAVA%
md %EXCHANGE_TARGET_JAVA%

copy .\target\rest-spring6-0.0.1-SNAPSHOT.war .\docker\jvm_alpine\rest-spring.war 
copy .\exchange\ %EXCHANGE_TARGET_JAVA%