FROM eclipse-temurin:25-jre

WORKDIR /app

COPY target/maventest-1.0-SNAPSHOT.jar app.jar

EXPOSE 18080

ENTRYPOINT ["java", "-jar", "app.jar"]
