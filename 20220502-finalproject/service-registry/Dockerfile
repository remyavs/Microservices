# Maven build container

FROM maven:3.8.5-openjdk-11 AS maven_build

COPY pom.xml /tmp/

COPY src /tmp/src/

WORKDIR /tmp/

RUN mvn package

#pull base image

FROM openjdk

#maintainer
MAINTAINER remyavs.21@gmail.com
#expose port 8080
EXPOSE 8761

#default command
CMD java -Dvertx.disableDnsResolver=true -jar /data/service-registry-0.0.1-SNAPSHOT.jar

#copy hello world to docker image from builder image

COPY --from=maven_build /tmp/target/service-registry-0.0.1-SNAPSHOT.jar /data/service-registry-0.0.1-SNAPSHOT.jar
