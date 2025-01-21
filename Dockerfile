FROM maven:3-eclipse-temurin-17 AS builder
WORKDIR /app
COPY src pom.xml /app/
RUN mvn -DskipTests clean package

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/
EXPOSE 8080
CMD ["java","-jar","/app/LibraryManagement-0.0.1-SNAPSHOT.jar"]