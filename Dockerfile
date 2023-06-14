FROM openjdk:11
ARG JAR_FILE=target/*.jar
ADD target/E-Wallet-API-with-SpringBoot-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8086
d