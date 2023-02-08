#!/bin/sh

mvn clean package -DskipTests

sleep 1

cd ../../../../../classes-service
e
docker build -t classes-service:latest .

docker-compose up