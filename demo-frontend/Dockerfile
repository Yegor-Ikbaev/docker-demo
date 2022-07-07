FROM gradle:7.4.1-jdk17-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:17.0.1-jdk-slim

EXPOSE 8081

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/demo-0.0.1-SNAPSHOT.jar /app/spring-boot-application.jar

ENV SPRING_PROFILES_ACTIVE=dev

ENTRYPOINT ["java","-jar","/app/spring-boot-application.jar"]