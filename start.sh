#!/bin/bash

mvn clean package

docker build -t simple-search-engine-server --build-arg APPLICATION_TYPE=server .
docker build -t simple-search-engine-client --build-arg APPLICATION_TYPE=client .

docker-compose up --abort-on-container-exit
