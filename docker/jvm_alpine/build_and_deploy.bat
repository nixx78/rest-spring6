@echo off

set CONTAINER_NAME=REST-Spring

docker stop %CONTAINER_NAME%
docker rm %CONTAINER_NAME%

docker build -t nixx/restfull-spring .
docker run  -v .\..\_exchange:/rest-spring/exchange --name %CONTAINER_NAME% -d -h=webserver -p 8081:8080 nixx/restfull-spring
