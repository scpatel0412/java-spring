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
