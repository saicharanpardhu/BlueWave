FROM java:8-jre
ADD target/configserver-0.0.1-SNAPSHOT.jar config.jar
ENTRYPOINT ["java","-Xmx200m", "-jar", "/config.jar"]
EXPOSE 8888