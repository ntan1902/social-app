# base image
FROM node:alpine

# set working directory
WORKDIR /app

# Build args
ARG REACT_APP_PUBLIC_FOLDER
ARG REACT_APP_API_URL

# Environment vars
ENV REACT_APP_PUBLIC_FOLDER=$REACT_APP_PUBLIC_FOLDER
ENV REACT_APP_API_URL=$REACT_APP_API_URL

# install app dependencies
COPY package.json ./

COPY package-lock.json ./

RUN npm install

# add app
COPY . ./

# start app
CMD ["npm", "start"]