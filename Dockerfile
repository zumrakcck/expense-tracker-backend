# 🚀 Java 17 tabanlı imaj kullan
FROM eclipse-temurin:17-jdk-alpine

# Uygulama jar dosyasını kopyala
COPY target/*.jar app.jar

# Uygulamayı başlat
ENTRYPOINT ["java", "-jar", "/app.jar"]
