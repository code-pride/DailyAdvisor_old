# Dockerfile for FRONTEND container
FROM node:latest

RUN mkdir -p /frontend
WORKDIR /frontend

ADD /frontend /frontend

VOLUME ["/frontend"]

EXPOSE 3000
CMD npm install && npm run serve
