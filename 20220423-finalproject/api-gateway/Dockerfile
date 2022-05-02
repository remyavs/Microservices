# Maven build container

FROM maven:3.8.5-openjdk-11 AS maven_build

COPY pom.xml /tmp/gateway/

COPY src /tmp/gateway/src/

WORKDIR /tmp/gateway/

RUN mvn package

#pull base image

FROM openjdk

#maintainer
MAINTAINER remyavs.21@gmail.com
#expose port 8080
EXPOSE 8989

#default command
CMD java -jar -Dvertx.disableDnsResolver=true /data/gateway/cloud-gateway-0.0.1-SNAPSHOT.jar
#copy hello world to docker image from builder image

COPY --from=maven_build /tmp/gateway/target/cloud-gateway-0.0.1-SNAPSHOT.jar  /data/gateway/cloud-gateway-0.0.1-SNAPSHOT.jar
