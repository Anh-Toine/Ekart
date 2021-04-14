#!/usr/bin/env bash
mkdir microservices
cd microservices

spring init \
--boot-version=2.3.2.RELEASE \
--build=gradle \
--java-version=1.8 \
--packaging=jar \
--name=supplier-service \
--package-name=com.nguyen.microservices.core.supplier \
--groupId=com.nguyen.microservices.core.supplier \
--dependencies=actuator,webflux \
--version=1.0.0-SNAPSHOT \
supplier-service

spring init \
--boot-version=2.3.2.RELEASE \
--build=gradle \
--java-version=1.8 \
--packaging=jar \
--name=item-service \
--package-name=com.nguyen.microservices.core.item \
--groupId=com.nguyen.microservices.core.item \
--dependencies=actuator,webflux \
--version=1.0.0-SNAPSHOT \
item-service

spring init \
--boot-version=2.3.2.RELEASE \
--build=gradle \
--java-version=1.8 \
--packaging=jar \
--name=supplier-service \
--package-name=com.nguyen.microservices.core.customer \
--groupId=com.nguyen.microservices.core.customer \
--dependencies=actuator,webflux \
--version=1.0.0-SNAPSHOT \
customer-service

cd ..
