#!/bin/sh

cd ../../../../../classes-service

mvn clean package -DskipTests

sleep 1

docker build -t classes-service:latest .