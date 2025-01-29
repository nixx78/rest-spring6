FROM openjdk:17-jdk-alpine

ENV VARIABLE_FROM_DOCKERFILE=VARIABLE_FROM_DOCKERFILE.VALUE

ARG WAR_FILE=rest-spring.war
ARG APP_FOLDER=rest-spring

RUN mkdir ${APP_FOLDER}
RUN mkdir ${APP_FOLDER}/images
RUN mkdir ${APP_FOLDER}/exchange

COPY ${WAR_FILE} ${APP_FOLDER}/${WAR_FILE}

COPY ./data/images/ /${APP_FOLDER}/images/
COPY ./_exchange/ /${APP_FOLDER}/exchange/

WORKDIR /${APP_FOLDER}

ENTRYPOINT ["java", "-jar", "rest-spring.war", "app.arg1, app.arg2"]