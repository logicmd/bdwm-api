#!/bin/bash
mvn clean package
mvn exec:java -Dexec.mainClass="us.hk.bdwm.api.WebServer" -Denv=debug
