# Stage 1: Build the application
FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /app

# Copy the Maven project descriptor and install dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and build the application
COPY src/ ./src/
RUN mvn clean package 

# Stage 2: Run the application
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy the packaged JAR file from the builder stage
COPY --from=builder /app/target/TravelinVServer-0.0.1-SNAPSHOT.jar /app/TravelinVServer.jar

# Set environment variables
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://travelinv-postgre.postgres.database.azure.com:5432/travelinv-postgre
ENV SPRING_DATASOURCE_USERNAME=azure_admin
ENV SPRING_DATASOURCE_PASSWORD=Baitaplonso7
ENV SPRING_DATASOURCE_DRIVER_CLASS_NAME=org.postgresql.Driver
ENV SPRING_SECURITY_USERNAME=admin
ENV SPRING_SECURITY_PASSWORD=admin


# Expose the port that the application will run on
EXPOSE 3100

# Define the command to run your application
CMD ["java", "-jar", "TravelinVServer.jar"]
