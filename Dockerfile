# Użyj JDK jako obrazu bazowego
FROM eclipse-temurin:17-jdk

# Ustaw katalog roboczy
WORKDIR /app

# Skopiuj plik JAR do kontenera (upewnij się, że jar ma poprawną nazwę)
COPY target/medapp-1.0.0.jar app.jar


# Ustaw port (opcjonalne, tylko informacyjne)
EXPOSE 8080

# Komenda startowa
ENTRYPOINT ["java", "-jar", "app.jar"]
