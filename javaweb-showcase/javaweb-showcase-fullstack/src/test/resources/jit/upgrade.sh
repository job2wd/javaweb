#!/bin/sh
echo "Shutdown JIT Server ..."
kill -TERM `ps -ef | grep java | grep -v grep | awk '{print $2}'`
echo "Shutdown MySQL Server ..."
kill -TERM `ps -ef | grep mysql | grep -v grep | awk '{print $2}'`
sleep 15
echo "Backup Orignal Software..."
DATE=`date +"%Y%m%d.%H%M%S"`
mkdir /home/tmp
tar zcf /home/tmp/backup_$DATE.tar.gz /home/mysql /home/assp/JIT/Cinas_CSS/CSSServer 
echo "Upgrade Software..."
tar zxf upgrade.tsa.server_2.0.20b121.tar.gz
echo "Please roboot system!!!"
