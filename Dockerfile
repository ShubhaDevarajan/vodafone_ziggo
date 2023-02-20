# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src/ ./src/
COPY pom.xml .
RUN mvn -f ./pom.xml package

#
# Package stage
#
FROM openjdk:19
COPY --from=build ./target/Assignment-1.0-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java","-jar","Assignment-1.0-SNAPSHOT.jar"]