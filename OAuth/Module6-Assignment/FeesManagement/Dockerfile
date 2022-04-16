# Maven build container

FROM maven:3.8.5-openjdk-11 AS maven_build

COPY pom.xml /tmp/fee/

COPY src /tmp/fee/src/

WORKDIR /tmp/fee/

RUN mvn package

#pull base image

FROM openjdk

#maintainer
MAINTAINER remyavs.21@gmail.com
#expose port 8080
EXPOSE 8083

#default command
CMD java -jar /data/fee/FeesManagement-0.0.1-SNAPSHOT.jar

#copy hello world to docker image from builder image

COPY --from=maven_build /tmp/fee/target/FeesManagement-0.0.1-SNAPSHOT.jar  /data/fee/FeesManagement-0.0.1-SNAPSHOT.jar
