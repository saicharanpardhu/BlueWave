FROM java:8-jre
ADD ./target/Persistence-0.0.1-SNAPSHOT.jar PersistenceManagerApplication.jar
ENTRYPOINT ["java","-Xmx200m", "-jar", "/PersistenceManagerApplication.jar"]
EXPOSE 8055

