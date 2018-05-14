#FROM java:9-jre-apline
#FROM openjdk:9-jre-alpine
FROM openjdk:jre-alpine
#VOLUME /tmp
#ARG JAR_FILE
#COPY ${JAR_FILE} app.jar
COPY target/spring-boot-hello-world.jar app.jar
EXPOSE 8080
EXPOSE 5701
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

