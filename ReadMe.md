# Приложение для демонстрации использования Spring REST
Приложение "песочница" для примеров REST и запуска приложения в Docker контейнерах.

## Варианты запуска приложения
* Запуск приложения из WAR (embedded Tomcat) локально (java -jar rest-spring6-0.0.1-SNAPSHOT.war)
* Запуск приложения на Tomcat (Docker - tomcat)
* Запуск приложения из WAR в докер контейнере (Docker - embedded_tomcat)

## Основные случаи использования
* Контролеры c различными HTTP методами
* Контролер, который использует record в качестве параметра и возвращаемого значения 
* Использование валидации входных данных @Validate/@Valid
* Перехват exception и формирование ответа
* Запись версии в MANIFEST.MF при сборке проекта и получение при запросе REST(GET /appInfo)
* Создание контролера "на лету" в конфигурационном файле (регистрация в RequestMappingHandlerMapping)
* Контроллер для операций с файлами
    * Upload файла с сохранением в файловой системе
    * Download файла из файловой системы
    * Получение списка файлов из директории
* JacksonConfig - Регистрация модуля для особой десериализации строк в JSON. Удаление пробелов в начале и конце строки
* Получение доступа к статичным ресурсам (data/images). Пример конфигурации в application.yaml и в Java
 
## Application endpoints
- Embedded Tomcat Local: http://localhost:8080/rest-spring/swagger-ui.html
- Embedded Tomcat on Docker: http://localhost:8081/rest-spring/swagger-ui.html
- Tomcat on Docker: http://localhost/rest-spring/swagger-ui.html
- Tomcat on Docker (запуск из runtime_using_compose): http://localhost:88/rest-spring/swagger-ui.html

## Cылки
* https://spring.io/guides/gs/spring-boot-docker/
* https://medium.com/holisticon-consultants/dont-build-fat-jars-for-docker-applications-6252a5571248

## Примечания
spring-boot-maven-plugin - нужен только для запуска приложения из WAR файла, если приложения будет
развертываться на Tomcat сервере, то данных плагин можно не включать. 

