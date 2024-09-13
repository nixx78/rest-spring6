# Приложение для демонстрации использования Spring REST 
Приложение "песочница" для примеров REST и запуска приложения в Docker контейнерах.

* Запуск приложения из WAR (embedded Tomcat) локально 
* Запуск приложения на Tomcat (Docker - tomcat)
* Запуск приложения из WAR (Docker - jvm_alpine)
* Запуск приложения из WAR (Docker - composite (dockerfile + docker-compose))

## Основные случаи использования
* Controller c различными HTTP методами
* Использование валидации входных данных @Validate/@Valid
* Перехват exception и формирование ответа
* Запись версии в MANIFEST.MF при сборке проекта и получение при запросе REST(GET /appInfo)
* Деплой приложения на Tomcat сервер с профилем
* Создание контролера "на лету" в конфигурационном файле (регистрация в RequestMappingHandlerMapping)
* Контроллер для операций с файлами
    * Upload файла с сохранением 
    * Download файла из файловой системы
    * Получение списка файлов из директории

## Запуск приложения из WAR  
Необходимо, чтобы например протестировать извлечение версии их MANIFEST файла: java -jar rest-spring6-0.0.1-SNAPSHOT.war

## Application endpoints
- Embedded Tomcat Local: http://localhost:8080/rest-spring/swagger-ui.html
- Embedded Tomcat on Docker: http://localhost:8081/rest-spring/swagger-ui.html
- Tomcat on Docker: http://localhost/rest-spring/swagger-ui.html
- Tomcat on Docker (Docker file + Compose): http://localhost:88/rest-spring/swagger-ui.html

## Cылки
* https://spring.io/guides/gs/spring-boot-docker/
* https://medium.com/holisticon-consultants/dont-build-fat-jars-for-docker-applications-6252a5571248