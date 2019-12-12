from openjdk:8-jdk-alpine
volume /tmp
copy ./target/*.jar app.jar
entrypoint ["java", "-jar", "/app.jar"]
expose 8080 8080
