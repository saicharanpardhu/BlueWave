FROM java:8-jre
ADD target/Api-Gateway-0.0.1-SNAPSHOT.jar api-gateway.jar
ENTRYPOINT ["java","-Xmx200m", "-jar", "/api-gateway.jar"]
EXPOSE 8090
