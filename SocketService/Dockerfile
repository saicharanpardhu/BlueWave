FROM java:8-jre
ADD ./target/gs-messaging-stomp-websocket-0.1.0.jar SocketService.jar
ENTRYPOINT ["java","-Xmx200m", "-jar", "/SocketService.jar"]
EXPOSE 3000
