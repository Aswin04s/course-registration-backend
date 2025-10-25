FROM openjdk:17-slim

WORKDIR /app

COPY . .

# Debug: Show environment variables
RUN echo "Environment variables:"
RUN env

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# Debug: Show built JAR
RUN ls -la target/

EXPOSE 10000

CMD ["java", "-jar", "target/course-registration-system-0.0.1-SNAPSHOT.jar"]