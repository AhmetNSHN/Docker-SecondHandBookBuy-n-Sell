FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} sell-book-docker.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","/sell-book-docker.jar"]