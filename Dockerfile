FROM openjdk:18
ADD target/spring-mvc-1.0-SNAPSHOT.jar spring-mvc.jar
ENTRYPOINT ["java", "-jar","spring-mvc.jar"]
EXPOSE 8080