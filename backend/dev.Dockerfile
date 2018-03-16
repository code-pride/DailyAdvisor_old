# Dockerfile for BACKEND container
# FROM openjdk:8-jdk

# FROM openjdk:8-jre-alpine

# RUN mkdir -p /backend
# WORKDIR /backend

# ADD /backend /backend

# RUN apt-get update && apt-get install -y maven

# RUN ls -l

# EXPOSE 8080
# RUN mvn clean install
# RUN mvn package
# RUN ls -l

# VOLUME ["/backend"]

# RUN ls -l target/

# CMD java -jar target/DailyAdvisor-0.0.1-SNAPSHOT.jar




FROM openjdk:8-jre-alpine
COPY . /backend
WORKDIR /backend
VOLUME ["/backend"]
EXPOSE 8080
ENTRYPOINT ["java","-jar","./target/DailyAdvisor-0.0.1-SNAPSHOT.jar"]




# FROM openjdk:8-jdk
# WORKDIR /backend
# ADD /backend /backend
# RUN apt-get update && apt-get install -y maven
# ADD . /backend
# EXPOSE 8080
# ENTRYPOINT ["mvn", "spring-boot:run"]
