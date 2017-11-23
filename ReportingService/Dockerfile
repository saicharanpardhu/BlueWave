FROM java:8-jre
ADD ./target/Reports_Cassandra-0.0.1-SNAPSHOT.jar reporting.jar
ENTRYPOINT ["java", "-jar", "/reporting.jar"]
EXPOSE 8040

