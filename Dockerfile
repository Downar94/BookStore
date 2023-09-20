FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/bookshop-0.0.1-SNAPSHOT.jar /app/bookshop-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java", "-jar", "bookshop-0.0.1-SNAPSHOT.jar"]