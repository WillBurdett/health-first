#!/bin/sh


mvn clean package -DskipTests

sleep 1

docker build -t classes-service:latest .

sleep 1

docker-compose -f docker-compose.yml up