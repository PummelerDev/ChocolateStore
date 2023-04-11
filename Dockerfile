FROM openjdk:11
RUN mkdir /app
WORKDIR /app
COPY target/ChocolateStore-0.0.1-SNAPSHOT.jar /app
ENTRYPOINT java -jar /app/ChocolateStore-0.0.1-SNAPSHOT.jar