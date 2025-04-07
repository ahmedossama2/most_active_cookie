#!/bin/bash
mvn clean package
java -jar target/most-active-cookie-1.0.0.jar "$@"
