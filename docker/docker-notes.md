# Общие сведения о Docker & примеры использования

## Структура примеров docker

### Embedded Tomcat
В данной директории собрано 3 примера.
* Запуск image из Docker файла при помощи команды docker run (build_and_run.bat)
* Запуск image при помощи Dockerfile + docker-compose.yaml (docker compose up -d)
* Запуск image при помощи docker-compose.yaml при этом, image берется из repository (runtime_using_compose)

### tomcat
Пример использования, в котором создается image с tomcat при помощи compose.yml

## Network
- Create network: _docker network create **docker-net**_
- Info about network:  _docker network inspect **docker-net**_
- Получение списка сетей: _docker network ls_

## Основные Docker команды 
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
# Работа с Docker repository

## Работа с image в репозиторий Docker Hub
**Общий вид команд**
* docker tag <local-image>:<tagname> <remote-repo>:<tagname>
* docker push <remote-repo>:<tagname>

**Пример**
* docker tag nixx/rest-spring-embedded-image:latest nixx78/sandbox:latest
* docker push nixx78/sandbox:latest

## Получение image из репозитория
**Общий вид команд**
* docker push <remote-repo>:<tagname>

**Пример**
* docker pull nixx78/sandbox:latest
* После команды pull, можно проверить наличие image при помощи команды docker images

**Запуск**
Перед запуском при помощи docker-compose файла, можно получить свежий образ
при помощи команды docker-compose pull

# Типовые сценарии 

## Cборка и помещение Image в Docker Hub

### Сборка Image
В этом шаге, при помощи команды docker build происходит сборка образа
Пример: docker build -t my_image_name:1.23 .  
Примечание: 
* при помощи -t задается имя образа и tag (my_image_name:1.23)
* . - означает путь, относительно которого производится сборка

### Добавляем тег с именем в Docker Hub
Данный шаг можно пропустить, если сразу использовать правильное имя/tag - например: nixx78/sandbox:1.123
Команда: docker tag my_image_name:1.23 nixx78/sandbox:1.123

### Загружаем в Docker Hub:
Происходит при помощи команды: docker push nixx78/sandbox:1.123

### Дополнительные действия
Можно загрузить образ из Docker Hub в локальное хранилище при помощи команды: docker pull nixx78/sandbox:1.123  
Содержимое локального репозитория можно посмотреть при помощи команды: docker images
