FROM openjdk:11-jdk-slim

# APPLICATION_TYPE = one of the [client, server]
ARG APPLICATION_TYPE
ADD ./demo-${APPLICATION_TYPE}/target/demo-${APPLICATION_TYPE}-1.0.0-SNAPSHOT.jar /home/java/app.jar

EXPOSE 8080/tcp

ENTRYPOINT exec java -jar -Dspring.profiles.active=docker /home/java/app.jar
