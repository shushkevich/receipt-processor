FROM openjdk:17-jdk-slim AS build
WORKDIR /app
COPY mvnw /app/mvnw
COPY .mvn /app/.mvn
COPY pom.xml /app/pom.xml
COPY src /app/src/
RUN chmod +x mvnw
RUN ./mvnw clean package

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]