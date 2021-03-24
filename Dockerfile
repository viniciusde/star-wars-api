FROM openjdk:8-jdk-alpine
VOLUME /star-wars-api
ADD target/StarWarsAPI-1.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/app.jar"]