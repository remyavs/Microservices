# Maven build container

FROM maven:3.8.5-openjdk-11 AS maven_build

COPY pom.xml /tmp/student/

COPY src /tmp/student/src/

WORKDIR /tmp/student/

RUN mvn package

#pull base image

FROM openjdk

#maintainer
MAINTAINER remyavs.21@gmail.com
#expose port 8080
EXPOSE 8084

#default command
CMD java -jar /data/student/StudentManagement-0.0.1-SNAPSHOT.jar

#copy hello world to docker image from builder image

COPY --from=maven_build /tmp/student/target/StudentManagement-0.0.1-SNAPSHOT.jar  /data/student/StudentManagement-0.0.1-SNAPSHOT.jar
