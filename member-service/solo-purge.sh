#!/bin/sh

docker-compose down

docker image rm member-service

rm target/member-service*

