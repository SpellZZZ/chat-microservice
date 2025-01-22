FROM jelastic/maven:3.9.5-openjdk-17 AS build
WORKDIR /home/app

COPY ./pom.xml /home/app/pom.xml
COPY ./src /home/app/src

RUN mvn -f /home/app/pom.xml clean package -DskipTests

FROM amazoncorretto:17-alpine3.18
COPY --from=build /home/app/target/*.jar ConfigService.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "ConfigService.jar"]