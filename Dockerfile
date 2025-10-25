FROM openjdk:17-slim

WORKDIR /app

COPY . .

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/course-registration-system-0.0.1-SNAPSHOT.jar"]