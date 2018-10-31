#!/bin/sh
#Stop all java grep

echo "Shutdown JIT Server ..."
pid=`ps -ef | grep java |grep CSSServer | grep -v grep | awk '{print $2}'`
if [ "${pid}" = "" ]
then
  echo "JIT Server is not start"
else
  kill -TERM ${pid}
  echo "JIT Server Stoped"
fi
