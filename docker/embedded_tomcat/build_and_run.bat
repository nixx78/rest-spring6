@echo off

set CONTAINER_NAME=REST-Spring
set IMAGE_NAME=rest-spring-embedded-image

docker stop %CONTAINER_NAME%
docker rm %CONTAINER_NAME%

docker build -t nixx/%IMAGE_NAME% .
docker run  -v .\..\_exchange:/rest-spring/exchange --name %CONTAINER_NAME% -d -h=web_hostname -p 8081:8080 nixx/%IMAGE_NAME%
