FROM openjdk:8-jdk-alpine

RUN mkdir -p /backend
WORKDIR /backend

ADD /backend /backend

VOLUME ["/backend"]

ENTRYPOINT [ "sh", "./updateAndRun.sh" ]









