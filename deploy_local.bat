call mvn clean
echo "clean ok" 
mvn package -fpom_deploy.xml -Dmaven.test.skip=true -Plocal