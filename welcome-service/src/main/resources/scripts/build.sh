#!/bin/sh

cd ../../../../../welcome-service

mvn clean package -DskipTests

sleep 1

docker build -t welcome-service:latest .