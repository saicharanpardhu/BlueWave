FROM java:8-jdk-alpine
ADD ./target/Persistence-0.0.1-SNAPSHOT.jar PersistenceManagerApplication.jar
RUN sh -c 'touch /PersistenceManagerApplication.jar'
ENTRYPOINT ["java", "-Dspring.data.mongodb.uri=mongodb://databasemongo/users","-Djava.security.egd=file:/dev/./urandom","-jar","/PersistenceManagerApplication.jar"]
EXPOSE 8232







