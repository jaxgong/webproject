call mvn clean
echo "clean ok" 
mvn package -fpom.xml -Dmaven.test.skip=true -Plocal