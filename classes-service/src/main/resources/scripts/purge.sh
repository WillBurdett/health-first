#!/bin/sh

cd ../../../../../classes-service

docker-compose down

docker image rm classes-service

rm target/classes-service*

mvn clean