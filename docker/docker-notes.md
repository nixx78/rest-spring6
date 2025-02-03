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
- Сборка Docker image из докер файла, который находится в локальный директории: _docker build -t <IMAGE_NAME> ._
- Запуск Docker image: _docker run <IMAGE_ID>_
- Stop Docker container: _docker stop CONTAINER_NAME> _
- Remove Docker container:  _docker rm CONTAINER_NAME> _
- Получение информации о контейнерах: _docker ps_  _docker ps_
- Console for Application: _docker exec -it CONTAINER_NAME> /bin/sh_
- Получение информации о image: _docker image ls_
- Получение списка image в локальном хранилище: _docker images_
- Запуск из compose файла: _docker compose up -d_
- Остановка при запуске из compose файла: _docker compose down_