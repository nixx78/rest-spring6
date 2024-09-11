# Приложение для демонстрации использования Spring REST 

## Основные случаи использования

* Controller c различными HTTP методами
* Использование валидации входных данных @Validate/@Valid
* Перехват exception и формирование ответа
* Запись версии в MANIFEST.MF и возврат при помощи REST(GET /appInfo)
* Деплой приложения на Tomcat сервер с профилем
* Создание контролера "на лету" в конфигурационном файле (регистрация в RequestMappingHandlerMapping)
* Контроллер для операций с файлами
    * Upload файла с сохранением 
    * Download файла из файловой системы
    * Получение списка файлов из директории

## Application endpoint:
http://localhost:8080/rest-spring/swagger-ui/index.html

## Запуск приложения из WAR  
Необходимо, чтобы например протестировать извлечение версии их MANIFEST файла
* java -jar rest-spring6-0.0.1-SNAPSHOT.war 