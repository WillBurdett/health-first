#!/bin/sh

cd ../../../../../member-service

docker-compose down

docker image rm member-service

rm target/member-service*

mvn clean