FROM java:8-jre
ADD ./target/JobManager-0.0.1-SNAPSHOT.jar jobmanager.jar
ENTRYPOINT ["java","-Xmx200m", "-jar", "/jobmanager.jar"]
EXPOSE 8021

