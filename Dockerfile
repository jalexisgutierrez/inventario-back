# Stage 1 - build with maven
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# copy only pom first to cache deps
COPY pom.xml .
RUN mvn -q -DskipTests dependency:resolve

# copy sources and build
COPY src ./src
RUN mvn -q package -DskipTests

# Stage 2 - runtime
FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]
