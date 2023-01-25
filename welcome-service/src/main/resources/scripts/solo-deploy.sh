#!/bin/sh


mvn clean package -DskipTests

sleep 1

docker build -t welcome-service:latest .

docker-compose up