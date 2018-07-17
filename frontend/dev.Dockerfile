FROM node:latest

RUN mkdir -p /frontend
WORKDIR /frontend

ADD /frontend /frontend

VOLUME ["/frontend"]

EXPOSE 3000
EXPOSE 9876
CMD npm install && npm run start
