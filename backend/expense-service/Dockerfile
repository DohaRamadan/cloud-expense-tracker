FROM adoptopenjdk:17-jdk-hotspot
WORKDIR /app
COPY target/expense-service-0.0.1-SNAPSHOT.jar expense-service.jar
EXPOSE 8000
CMD ["java", "-jar", "expense-service.jar"]
