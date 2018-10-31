#!/bin/sh
# -----------------------------------------------------------------------------
# Start-up script for the JIT SignServer Server
#
# $Id: start.debug.sh,v 1.1 2009/09/25 02:15:30 weidong_wang Exp $
# -----------------------------------------------------------------------------

#JMX_OPTS="-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=9999 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false"; export JMX_OPTS 
JAVA_OPTS="-Xms128m -Xmx2048m -XX:PermSize=128M -XX:MaxNewSize=256m -XX:MaxPermSize=256m $JMX_OPTS"; export JAVA_OPTS

cd /home/assp/JIT/Cinas_CSS/CSSServer;./bin/catalina.sh jpda start
