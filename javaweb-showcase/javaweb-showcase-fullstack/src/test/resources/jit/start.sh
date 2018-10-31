#!/bin/sh
# -----------------------------------------------------------------------------
# Start-up script for the JIT SignServer Server
#
# $Id: start.sh,v 1.4 2009/12/30 12:39:25 zhao Exp $
# -----------------------------------------------------------------------------

SIGNSERVER_HOME=/home/assp/JIT/Cinas_CSS/CSSServer

#JMX_OPTS="-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=9999 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false"; export JMX_OPTS 
JAVA_OPTS="-Xms128m -Xmx1800m -XX:PermSize=128M -XX:MaxNewSize=256m -XX:MaxPermSize=256m $JMX_OPTS"; export JAVA_OPTS

# P11 Hardware Lib
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:$SIGNSERVER_HOME/lib/native:$SIGNSERVER_HOME/lib/native/swxa

cd $SIGNSERVER_HOME;./bin/catalina.sh start
