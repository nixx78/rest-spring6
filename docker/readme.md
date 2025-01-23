# Общие сведения о Docker

## Структура примеров docker

## Composite

Пример использования, в котором используется комбинированный способ сборки: Docker + compose.yml. В качестве
артефакта используется Spring Boot REST приложение с Embedded Tomcat.

## jvm_alpine
Пример использования, в котором image создается только при помощи Docker файла

## tomcat
Пример использования, в котором создается image с tomcat при помощи compose.yml

## Network
- Create network: _docker network create **docker-net**_
- Info about network:  _docker network inspect **docker-net**_
- Получение списка сетей: _docker network ls_

## Docker
- Build Docker image:
  _docker build -t <IMAGE_NAME> ._

- Run Docker:
 - docker run <IMAGE_ID>

- docker run  -v .\exchange:/rest-spring/exchange --name <CONTAINER_NAME> -d -h=webserver -p 8081:8080 <IMAGE_NAME>

- Run Docker with volume mapper:
  
- Stop Docker:
  _docker stop CONTAINER_NAME> _

- Remove Docker:
  _docker rm CONTAINER_NAME> _

- Получение информации о контейнерах
_docker ps_  _docker ps_

- Console for Application
  _docker exec -it CONTAINER_NAME> /bin/sh_

- Получение информации о image: _docker image ls_ 


