#!/bin/sh

cd ../../../../../config-server

docker-compose down

docker image rm config-server

rm target/config-server*

mvn clean