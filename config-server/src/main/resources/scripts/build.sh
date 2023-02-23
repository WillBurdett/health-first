#!/bin/sh

cd ../../../../../config-server

mvn clean package -DskipTests

sleep 1

docker build -t config-server:latest .