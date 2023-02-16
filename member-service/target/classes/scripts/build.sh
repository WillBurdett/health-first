#!/bin/sh

cd ../../../../../member-service

mvn clean package -DskipTests

sleep 1

docker build -t member-service:latest .