#!/bin/sh

#
# Shutdown MySQL and Signserver service
#
#Shutdown Signserver service
cd /home/assp/JIT/Cinas_CSS/CSSServer;./stop.java.sh
#Shutdown MySQL service
cd /home/mysql;./mysql.server stop

#
# 1. Initialize MySQL System Database
#
rm -rf /home/mysql/var
su mysql -c /home/mysql/bin/mysql_install_db
cd /home/mysql;./mysql.server start
/home/mysql/bin/mysql -u root mysql < /home/assp/JIT/Cinas_CSS/CSSServer/schema/mysql/init_pwd.sql

#
# 2. Initialize Mysql Database for Signserver
#
/home/mysql/bin/mysql -u root -padmin123 < /home/assp/JIT/Cinas_CSS/CSSServer/schema/mysql/mysql.sql

#
# 3. Initialize Config and Store
#
rm -rf /home/assp/JIT/Cinas_CSS/CSSServer/store
cp -r /home/assp/JIT/Cinas_CSS/CSSServer/webapps/signserver/WEB-INF/classes/store /home/assp/JIT/Cinas_CSS/CSSServer

# Select jit crypto session mode
SIGNSERVER_HOME=/home/assp/JIT/Cinas_CSS/CSSServer
# P11 Hardware Lib
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:$SIGNSERVER_HOME/lib/native:$SIGNSERVER_HOME/lib/native/swxa
./check_sw_card
return_code=$?
echo $return_code
if [ $return_code == "0" ]
then
  echo "SW Card is present in this device"
  echo "Select jit crypto session mode"
  select var in "software" "hardware" "highperf"; do
  break
  done
  echo "You have selected $var"
  sed "s/>software</>$var</" /home/assp/JIT/Cinas_CSS/CSSServer/webapps/signserver/WEB-INF/classes/store/config.xml > /home/assp/JIT/Cinas_CSS/CSSServer/store/config.xml
else
  echo "Error to find or init SW Card"
  echo "Use default crypto session mode :software mode"
fi

#
# 4. Clear log files
#
rm -rf /home/assp/JIT/Cinas_CSS/CSSServer/logs/*

#
#Start MySQL and Signserver service
#
#Start Signserver service
cd /home/assp/JIT/Cinas_CSS/CSSServer;./start.sh

echo "Initialize Successful"

