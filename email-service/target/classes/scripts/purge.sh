#!/bin/sh

cd ../../../../../email-service

docker-compose down

docker image rm email-service

rm target/email-service*

mvn clean