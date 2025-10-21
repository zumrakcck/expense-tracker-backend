# 1️⃣ Maven ile uygulamayı build et
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# 2️⃣ Java 17 imajı ile çalıştır
FROM eclipse-temurin:17-jdk-alpine
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
