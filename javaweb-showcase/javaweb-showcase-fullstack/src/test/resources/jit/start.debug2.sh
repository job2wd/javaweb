export JAVA_HOME=/usr/jdk1.6.0_20
export JRE_HOME=/usr/jdk1.6.0_20/jre
export PATH=$JAVA_HOME/bin:$PATH
export CLASSPATH=./:$JAVA_HOME/lib:$JRE_HOME/lib:/GoldCardRun/bin/bootstrap.jar
export JDA_OPTS="-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8787,server=y,suspend=n"
# echo $JAVA_HOME
# echo $JRE_HOME
ulimit -n 4096
$JRE_HOME/bin/java $JDA_OPTS
$JAVA_HOME/bin/java -server -Xmx4096m -Xms4096m -Xmn1024m -Xss128k -XX:+UseConcMarkSweepGC -XX:CMSFullGCsBeforeCompaction=5 -XX:+UseCMSCompactAtFullCollection server.Bootstrap start
