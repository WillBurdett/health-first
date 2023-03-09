#!/bin/sh

cd ../../../../../naming-server

docker-compose down

docker image rm naming-server

rm target/naming-server*

mvn clean