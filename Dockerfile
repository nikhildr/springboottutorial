FROM openjdk:11-slim as build
MAINTAINER nikilkumar.1988@gmail.com
COPY target/spring-boot-application-1.0.0.jar spring-boot-application-1.0.0.jar
ENV PORT 8081
EXPOSE $PORT
ENTRYPOINT ["java","-jar","/spring-boot-application-1.0.0.jar"]