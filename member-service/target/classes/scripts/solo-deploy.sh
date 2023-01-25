#!/bin/sh

mvn clean package -DskipTests

sleep 1

docker build -t member-service:latest .

docker-compose up