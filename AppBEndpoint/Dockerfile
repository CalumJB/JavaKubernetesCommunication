FROM openjdk:11
COPY ./target/AppBEndpoint-0.0.1-SNAPSHOT.jar /usr/app/
ENV PASSED_VALUE=default
WORKDIR /usr/app
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "AppBEndpoint-0.0.1-SNAPSHOT.jar"]