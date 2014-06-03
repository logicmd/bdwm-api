#!/bin/bash

SERVICE=bdwm-api
INSTALL_DIR=~/install-bdwm-api

cd ../../

if [ $# -ne 1 ];then
    echo "install.sh <profile>"
    echo "profile: debug/staging/online"
    exit 1
fi

PROFILE=$1

mvn clean package -pl ${SERVICE} -am -Dmaven.test.skip -Denv=${PROFILE}

mkdir -p $INSTALL_DIR
mkdir -p $INSTALL_DIR/logs
mkdir -p $INSTALL_DIR/bin

#cp ${SERVICE}/target/$SERVICE.jar $INSTALL_DIR/$SERVICE.jar
#cp ${SERVICE}/bin/*.sh $INSTALL_DIR/bin


