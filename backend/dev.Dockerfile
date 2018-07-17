FROM openjdk:8-jdk-alpine

RUN mkdir -p /backend
WORKDIR /backend

ADD /backend /backend

VOLUME ["/backend"]


RUN ["sh", "gradlew", "clean"]
RUN ["sh", "gradlew", "build"]
RUN ["sh", "gradlew", "bootJar"]

RUN ls



ENTRYPOINT [ "java", "-jar", "build/libs/DailyAdvisor.jar" ]

