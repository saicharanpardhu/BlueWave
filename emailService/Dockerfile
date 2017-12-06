FROM java:8-jre
ADD target/EmailService-0.0.1-SNAPSHOT.jar email.jar
ENTRYPOINT ["java","-Xmx200m", "-jar", "/email.jar"]
EXPOSE 8094
