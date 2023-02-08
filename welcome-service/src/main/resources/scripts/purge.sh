#!/bin/sh

cd ../../../../../welcome-service

docker-compose down

docker image rm welcome-service

rm target/welcome-service*

mvn clean