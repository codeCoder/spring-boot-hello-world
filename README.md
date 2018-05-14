# spring-boot-hello-world
Example spring boot service with single endpoint

How to build:

mvn clean install && docker build -t xxxxxxx/spring-boot-hello-world:1 . && docker push xxxxxxx/spring-boot-hello-world:1


then change the tag in the deployment.yaml to deploy the newest built