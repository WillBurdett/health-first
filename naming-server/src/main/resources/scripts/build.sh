#!/bin/sh

cd ../../../../../naming-server

mvn clean package -DskipTests

sleep 1

docker build -t naming-server:latest .