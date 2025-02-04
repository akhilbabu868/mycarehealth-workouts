## the base image
#FROM openjdk:17-jdk-slim
##information about who maintain the image
#MAINTAINER akhil
##Add the applications jar to the image
#COPY target/user-api-docker.jar user-api-docker.jar
##For excute the Application
#ENTRYPOINT ["java", "-jar", "user-api-docker.jar"]
#
