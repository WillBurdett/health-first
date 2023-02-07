#!/bin/sh

cd ../../../../../email-service

docker image rm email-service

rm target/email-service*

mvn clean