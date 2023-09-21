# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

  # Set the working directory
WORKDIR /app

  # Copy the JAR file into the container
COPY target/trading-application-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot app is listening on
EXPOSE 8080

  # Specify the command to run your application
CMD ["java", "-jar", "app.jar"]