# Maven build container

FROM maven:3.8.5-openjdk-11 AS maven_build

COPY pom.xml /tmp/oauth/

COPY src /tmp/oauth/src/

WORKDIR /tmp/oauth/

RUN mvn package

#pull base image

FROM openjdk:11.0.14.1

#maintainer
MAINTAINER remyavs.21@gmail.com
#expose port 8080
EXPOSE 9000

#default command
CMD java -Dvertx.disableDnsResolver=true -jar /data/oauth/oauth-server-0.0.1-SNAPSHOT.jar

#copy hello world to docker image from builder image

COPY --from=maven_build /tmp/oauth/target/oauth-server-0.0.1-SNAPSHOT.jar  /data/oauth/oauth-server-0.0.1-SNAPSHOT.jar
