#!/bin/sh

docker-compose down

docker image rm classes-service

rm target/classes-service*

