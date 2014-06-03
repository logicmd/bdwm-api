#!/bin/bash
SERVICE=bdwm-api
LOG_DIR=logs
LOG_FILE=bdwm-api.log
OUT_FILE=bdwm-api.out
JAVA_OPTS="-Xms1024m -Xmx10240m -XX:+UseParallelGC -XX:ParallelGCThreads=4 -XX:+UseParallelOldGC -XX:YoungGenerationSizeIncrement=20 -XX:TenuredGenerationSizeIncrement=20 -Dstar.log.dir=${LOG_DIR} -Dstar.log.file=${LOG_FILE} -Dstar.root.logger=INFO,DRFA"

function usage() {
    echo "Usage: bash $0 command"
    echo "command:"
    echo "    start"
    echo "    stop"
    exit 1
}

if [ $# -ne 1 ];then
    usage
fi
COMMAND=$1
MAINCLASS="us.hk.bdwm.api.Application"

function stop() {
    PIDS=`ps x | grep ${MAINCLASS} | grep -v grep | grep java | awk '{print $1}'`
    for PID in ${PIDS};do
        kill -9 ${PID}
        echo "kill process from pid: ${PID}"
    done
}


function start() {
    cd ..
    CLASSPATH=${CLASSPATH}:${SERVICE}.jar
    export CLASSPATH
    mkdir ./logs >/dev/null 2>&1
    nohup java ${JAVA_OPTS} ${MAINCLASS} >${LOG_DIR}/${OUT_FILE} 2>${LOG_DIR}/${OUT_FILE} &
    sleep 3
    echo
    # show some logs by tail
    tail -n 10 ${LOG_DIR}/${LOG_FILE}
}

if [ ${COMMAND} == "start" ];then
    stop
    start
fi

if [ ${COMMAND} == "stop" ];then
    stop
fi
