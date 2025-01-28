# Общие сведения о Docker & примеры использования

## Структура примеров docker

### Embedded Tomcat
В данной директории собрано 3 примера.
* Запуск image из Docker файла при помощи команды docker run (buil_and_run.bat)
* Запуск image при помощи Dockerfile + docker-compose.yaml (docker compose up -d)
* Запуск image при помощи docker-compose.yaml при этом, image берется из repository (runtime_using_compose)

### tomcat
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


