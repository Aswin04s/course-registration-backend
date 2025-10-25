# Use a smaller JRE instead of JDK for production
FROM openjdk:17-jre-slim

# Install curl for health checks (optional but useful)
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

# Create app directory
WORKDIR /app

# Copy the JAR file from build stage (we'll build locally first)
COPY target/*.jar app.jar

# Expose port
EXPOSE 8080

# Health check (important for Render)
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]