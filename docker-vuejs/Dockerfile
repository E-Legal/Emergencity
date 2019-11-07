FROM node

COPY dashboard-src/package.json /var/dashboard-src

RUN npm install

RUN mkdir /var/dashboard-src
WORKDIR /var/dashboard-src

CMD sleep infinity