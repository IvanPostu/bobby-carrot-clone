#!/bin/bash

#build jar
bash mvnw clean test compile assembly:single

#create folder for demo
mkdir -p demo

#copy assembled app
cp ./target/bobbycarrot-1.0-SNAPSHOT-jar-with-dependencies.jar ./demo/

# rename app
mv ./demo/bobbycarrot-1.0-SNAPSHOT-jar-with-dependencies.jar ./demo/bobbycarot-clone.jar
