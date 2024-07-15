FROM maven:3.9.0-eclipse-temurin-17-alpine AS build
LABEL maintainer="Eduardo Assunção <eduardoasssousa@gmail.com>"
WORKDIR /usr/src/desafio-jovem-tech-dev
COPY ./pom.xml ./
COPY ./src ./src
RUN --mount=type=cache,target=/root/.m2 mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /usr/src/desafio-jovem-tech-dev/target/*.jar ./desafio-jovem-tech-dev.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "desafio-jovem-tech-dev.jar"]