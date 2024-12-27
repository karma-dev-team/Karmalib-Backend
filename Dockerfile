FROM maven:3.8.4-openjdk-17 as builder
WORKDIR /src
COPY . /src/.

