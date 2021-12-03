FROM maven:3.8.4-amazoncorretto-17 as build

WORKDIR /build

COPY . .

RUN mvn clean package -Dspring-boot.run.profiles=prod -DskipTests --batch-mode

FROM amazoncorretto:17-alpine3.15-jdk

COPY --from=build /build/target/spring-security-0.0.1-SNAPSHOT.jar /app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
