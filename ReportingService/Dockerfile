FROM java:8-jre
ADD ./target/Reports_Cassandra-0.0.1-SNAPSHOT.jar reporting-service.jar
ENTRYPOINT ["java", "-jar", "/reporting-service.jar"]
EXPOSE 8040

