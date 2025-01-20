FROM openjdk:17-jdk-slim AS build

WORKDIR /app

COPY pom.xml /app/pom.xml
COPY src /app/src

RUN apt-get update && apt-get install -y maven  # Install Maven
RUN mvn clean package -DskipTests  # Build the application, skip tests for faster build


FROM openjdk:17-jdk-slim

WORKDIR /app


COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar /app/demo-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]
# FROM openjdk:17-jdk-slim AS build

# WORKDIR /app

# # Copy pom.xml and source code
# COPY pom.xml /app/pom.xml
# COPY src /app/src
# COPY target /app/target

# # Install Maven and build the application (skip tests for faster build)
# RUN mvn clean package

# # Step 2: Create a smaller runtime image
# FROM openjdk:17-jdk-slim

# WORKDIR /app

# # Copy the JAR file from the build stage
# COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar /app/demo-0.0.1-SNAPSHOT.jar

# # Expose port 8080 for the app
# EXPOSE 8080

# # Run the Spring Boot application
# CMD ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]
