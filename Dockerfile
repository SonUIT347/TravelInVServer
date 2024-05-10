# Clean and rebuild jar file

# FROM maven:3.8.5-openjdk-17 as builder
# WORKDIR /app
# COPY pom.xml .
# RUN mvn dependency:go-offline
# COPY src/ ./src/
# RUN mvn clean package -DskipTests=true

# Use official OpenJDK 17 image as base
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app
# Copy the packaged JAR file into the container
ADD target/TravelinVServer-0.0.1-SNAPSHOT.jar /app/TravelinVServer.jar

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://travelinv.postgres.database.azure.com:5432/travelinv
ENV SPRING_DATASOURCE_USERNAME=admin_azure
ENV SPRING_DATASOURCE_PASSWORD=Baitaplonso7
ENV SPRING_DATASOURCE_DRIVER_CLASS_NAME=org.postgresql.Driver
ENV SPRING_SECURITY_USERNAME=admin
ENV SPRING_SECURITY_PASSWORD=admin
# Expose the port that the application will run on
EXPOSE 3100

# Define the command to run your application
CMD ["java", "-jar", "TravelinVServer.jar"]
