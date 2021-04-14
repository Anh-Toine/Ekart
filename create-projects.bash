#!/usr/bin/env bash
mkdir microservices
cd microservices

spring init \
--boot-version=2.3.2.RELEASE \
--build=gradle \
--java-version=1.8 \
--packaging=jar \
--name=supplier-service \
--package-name=com.nguyen.microservices.core.ekartcomposite \
--groupId=com.nguyen.microservices.core.ekartcomposite \
--dependencies=actuator,webflux \
--version=1.0.0-SNAPSHOT \
composite-service

cd ..
