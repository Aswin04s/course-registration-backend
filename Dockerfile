FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY target/course-registration-system-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 10000

CMD ["java", "-jar", "app.jar"]