FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/tkm-ms-test-utility-*.jar app.jar
COPY target/agent/applicationinsights-agent-*.jar agent.jar
ENTRYPOINT ["java", "-javaagent:agent.jar", "-jar", "app.jar"]