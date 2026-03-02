FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -q clean package -DskipTests
FROM eclipse-temurin:21-jre
RUN useradd -ms /bin/bash appuser
USER appuser
WORKDIR /home/appuser
COPY --from=build /app/target/payment-service-1.0.0.jar app.jar
EXPOSE 8086
ENTRYPOINT ["java","-jar","app.jar"]
