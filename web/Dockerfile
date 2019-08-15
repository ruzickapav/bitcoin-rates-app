FROM node:12.3.1 AS build

RUN mkdir /code
WORKDIR /code
COPY . /code
RUN npm install
RUN npm run build

FROM nginx:1.16.0-alpine

COPY public /usr/share/nginx/html
COPY nginx/conf /etc/nginx/conf.d
COPY --from=build /code/build /usr/share/nginx/html
