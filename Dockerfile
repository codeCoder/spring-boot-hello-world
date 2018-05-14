FROM openjdk:jre-alpine
COPY target/spring-boot-hello-world.jar app.jar
EXPOSE 8080
EXPOSE 5701
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

