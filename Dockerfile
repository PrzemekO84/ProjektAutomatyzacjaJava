FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY target/medapp-1.0.0.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
