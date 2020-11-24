FROM openjdk:11
COPY ./target/AppACaller-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "AppACaller-0.0.1-SNAPSHOT.jar"]