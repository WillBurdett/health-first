#!/bin/sh

mvn clean package -DskipTests

mvn spring-boot:start