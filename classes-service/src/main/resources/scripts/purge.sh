#!/bin/sh

cd ../../../../../classes-service

docker image rm classes-service

rm target/classes-service*

mvn clean