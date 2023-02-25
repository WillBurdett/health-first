#!/bin/sh

cd ../../../../../email-service

mvn clean package -DskipTests

sleep 1

docker build -t email-service:latest .