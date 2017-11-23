FROM java:8-jre
ADD target/Authorization-Server-0.0.1-SNAPSHOT.jar api-gateway.jar
ENTRYPOINT ["java","-Xmx200m", "-jar", "/api-gateway.jar"]
EXPOSE 8087
