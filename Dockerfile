FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/tkm-ms-test-utility-*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]