mvn -B -X -e -s D:\ProgramFiles\apache-maven-3.0\conf\settings.xml clean

Lifecycle default -> [validate, initialize, generate-sources, process-sources, generate-resources, process-resources, compile, process-classes, generate-test-sources, process-test-sources, generate-test-resources, process-test-resources, test-compile, process-test-classes, test, prepare-package, package, pre-integration-test, integration-test, post-integration-test, verify, install, deploy]
Lifecycle clean -> [pre-clean, clean, post-clean]
Lifecycle site -> [pre-site, site, post-site, site-deploy]


安装jar到本地容器
mvn install:install-file -Dfile=C:/classes12.jar -DgroupId=com.oracle -DartifactId=classes12 -Dversion=12 -Dpackaging=jar -DgeneratePom=true

发布jar到中心容器仓库
mvn deploy:deploy-file -DgroupId=com.oracle -DartifactId=classes12 -Dversion=12 -Dpackaging=jar -Dfile=C:/classes12.jar -Durl=http://localhost/nexus/content/repositories/bop-public-thirdparty -DrepositoryId=bop-public-thirdparty
