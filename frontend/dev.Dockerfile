# FROM node:9.11.1-alpine

# # install simple http server for serving static content
# RUN npm install -g http-server

# # make the 'app' folder the current working directory
# WORKDIR /app

# # copy both 'package.json' and 'package-lock.json' (if available)
# COPY package*.json ./

# # install project dependencies
# RUN npm install

# # copy project files and folders to the current working directory (i.e. 'app' folder)
# COPY . .

# EXPOSE 3000

# # build app for production with minification
# CMD npm install && npm run start





# Dockerfile for FRONTEND container
FROM node:latest

RUN mkdir -p /frontend
WORKDIR /frontend

ADD /frontend /frontend

VOLUME ["/frontend"]

EXPOSE 3000
EXPOSE 9876
CMD npm install && npm run start
