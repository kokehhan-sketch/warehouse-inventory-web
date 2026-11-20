# Use a Maven image with Java 17 to build the app
FROM maven:3.8.4-openjdk-17-slim

# Set the working directory
WORKDIR /app

# Copy the pom.xml and source code
COPY pom.xml .
COPY src ./src

# Build the application (skip tests to make deployment faster)
RUN mvn clean package -DskipTests

# Run the application
CMD ["java", "-jar", "target/inventory-0.0.1-SNAPSHOT.jar"]