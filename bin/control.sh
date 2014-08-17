#!/bin/bash
SERVICE=bdwm-api
LOG_DIR=logs
LOG_FILE=bdwm-api.log
OUT_FILE=bdwm-api.out
JAVA_OPTS="-Xms1024m -Xmx10240m -XX:+UseParallelGC -XX:ParallelGCThreads=4 -XX:+UseParallelOldGC -XX:YoungGenerationSizeIncrement=20 -XX:TenuredGenerationSizeIncrement=20"

usage() {
    echo "Usage: bash $0 command"
    echo "command:"
    echo "    start"
    echo "    stop"
    exit 1
}

if [ $# -ne 1 ]; then
    usage
fi

COMMAND=$1
MAINCLASS="us.hk.bdwm.api.WebServer"

stop() {
    PIDS=`ps x | grep ${MAINCLASS} | grep -v grep | grep java | awk '{print $1}'`
    for PID in ${PIDS};do
        kill -9 ${PID}
        echo "kill process from pid: ${PID}"
    done
}


start() {
    cd ..
    CLASSPATH=${CLASSPATH}:${SERVICE}.jar
    export CLASSPATH
    mkdir ./logs >/dev/null 2>&1
    nohup java ${JAVA_OPTS} ${MAINCLASS} >${LOG_DIR}/${OUT_FILE} 2>${LOG_DIR}/${OUT_FILE} &
    sleep 3
    echo
    # show some logs by tail
    tail -n 10 ${LOG_DIR}/${OUT_FILE}
}

echo ${COMMAND}

if [ "${COMMAND}"x = "start"x ]; then
    stop
    start
fi

if [ "${COMMAND}"x = "stop"x ]; then
    stop
fi
