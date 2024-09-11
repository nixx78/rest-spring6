@echo off
set EXCHANGE_PATH=.\docker\tomcat\exchange\

del /q %EXCHANGE_PATH%
md %EXCHANGE_PATH%

call mvn clean package
copy .\target\rest-spring6-0.0.1-SNAPSHOT.war .\docker\tomcat\rest-spring.war 
copy .\exchange\ %EXCHANGE_PATH%