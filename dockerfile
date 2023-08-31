FROM maven:3.8.5-openjdk-17-slim as builder
COPY . /buildDir
WORKDIR /buildDir
RUN mvn clean package
FROM maven:3.8.5-openjdk-17-slim
COPY --from=builder /buildDir/target/checkSelf-0.0.1-SNAPSHOT.jar app/springboot.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "springboot.jar"]
