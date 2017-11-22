FROM java:8-jre
ADD target/Reports_Cassandra-0.0.1-SNAPSHOT.jar Reporting-Service.jar
ENTRYPOINT ["java","-Xmx200m", "-jar", "/ Reporting-Service.jar"]
EXPOSE 8028
