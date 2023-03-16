#!/bin/sh

cd ../../../../../api-gateway

docker-compose down

docker image rm api-gateway

mvn clean