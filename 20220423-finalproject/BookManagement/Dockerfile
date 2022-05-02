# Maven build container

FROM maven:3.8.5-openjdk-11 AS maven_build

COPY pom.xml /tmp/book/

COPY src /tmp/book/src/

WORKDIR /tmp/book/

RUN mvn package

#pull base image

FROM openjdk

#maintainer
MAINTAINER remyavs.21@gmail.com
#expose port 8080
EXPOSE 8083

#default command
CMD java -jar /data/book/BookManagement-0.0.1-SNAPSHOT.jar

#copy hello world to docker image from builder image

COPY --from=maven_build /tmp/book/target/BookManagement-0.0.1-SNAPSHOT.jar  /data/book/BookManagement-0.0.1-SNAPSHOT.jar
