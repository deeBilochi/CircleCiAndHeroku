FROM openjdk:8-jdk

WORKDIR /home

ARG JAR_FILE=build/libs/springdemo-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-Xmx512m", "-Xms256m", "-jar", "app.jar"]