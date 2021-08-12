FROM openjdk:15-jdk-alpine

RUN tar xvf ./udodok-jar.tar

COPY ./*.jar app.jar

CMD [ "java", "-jar", "/app.jar" ]