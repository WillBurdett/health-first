#!/bin/sh

cd ../../../../../api-gateway

mvn clean package -DskipTests

sleep 1

docker build -t api-gateway:latest .