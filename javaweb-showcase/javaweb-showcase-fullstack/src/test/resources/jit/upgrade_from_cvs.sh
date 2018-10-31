#!/bin/sh
echo "Shutdown JIT Server ..."
kill -TERM `ps -ef | grep java | grep -v grep | awk '{print $2}'`
#echo "Shutdown MySQL Server ..."
#kill -TERM `ps -ef | grep mysql | grep -v grep | awk '{print $2}'`
sleep 15

echo "Backup Orignal Software..."
DATE=`date +"%Y%m%d.%H%M%S"`
mkdir /home/tmp
tar zcf /home/tmp/backup_$DATE.tar.gz /home/mysql /home/assp/JIT/Cinas_CSS/CSSServer
mv /home/assp/JIT/Cinas_CSS/CSSServer "/home/assp/JIT/Cinas_CSS/CSSServer.$DATE"

echo "Download upgrade package ..."
curl http://172.16.12.9:8082/job/JIT-SignServer-Assembly/ws/assembly/target/jit-signserver-2.0.20-SNAPSHOT-bin.zip -o /home/tmp/jit-signserver-2.0.20-SNAPSHOT-bin.zip

echo "Upgrade Software..."
cd /home/assp/JIT/Cinas_CSS
rm -rf /home/assp/JIT/Cinas_CSS/jit-signserver-2.0.20-SNAPSHOT
unzip /home/tmp/jit-signserver-2.0.20-SNAPSHOT-bin.zip
cp /home/assp/JIT/Cinas_CSS/CSSServer.$DATE/store/* /home/assp/JIT/Cinas_CSS/jit-signserver-2.0.20-SNAPSHOT/store/
mv /home/assp/JIT/Cinas_CSS/jit-signserver-2.0.20-SNAPSHOT /home/assp/JIT/Cinas_CSS/CSSServer
cd /home/assp/JIT/Cinas_CSS/CSSServer

echo "Starting Server ..."
./start.sh
