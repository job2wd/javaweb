安装jar到本地容器
mvn install:install-file -Dfile=C:/classes12.jar -DgroupId=com.oracle -DartifactId=classes12 -Dversion=12 -Dpackaging=jar -DgeneratePom=true

发布jar到中心容器仓库
mvn deploy:deploy-file -DgroupId=cn.com.jit.oldlib.signserver.2_0_15 -DartifactId=bcprov-jdk15 -Dversion=139 -Dpackaging=jar -Dfile=C:/bcprov-jdk15-139.jar -Durl=http://cvs.jit.com.cn:8081/nexus/content/repositories/thirdparty/ -DrepositoryId=ThirdParty
