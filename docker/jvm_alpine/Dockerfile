FROM openjdk:17-jdk-alpine

ARG WAR_FILE=rest-spring.war
ARG APP_FOLDER=rest-spring

RUN mkdir ${APP_FOLDER}

COPY ${WAR_FILE} ${APP_FOLDER}/${WAR_FILE}

WORKDIR /${APP_FOLDER}
ENV SPRING_PROFILES_ACTIVE=standalone
ENTRYPOINT ["java", "-jar", "rest-spring.war"]