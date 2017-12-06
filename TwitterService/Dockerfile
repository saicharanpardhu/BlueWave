FROM java:8-jre
ADD target/Twitter-0.0.1-SNAPSHOT.jar twitter.jar
ENTRYPOINT ["java","-Xmx200m", "-jar", "/twitter.jar"]
EXPOSE 8093
