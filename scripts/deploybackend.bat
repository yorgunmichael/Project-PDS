set m2=C:/Users/yorgu/.m2/repository/episen/backend-service/1.0-SNAPSHOT/backend-service-1.0-SNAPSHOT-jar-with-dependencies.jar
set project=C:/Users/yorgu/IdeaProjects/Project-PDS/backend-service/src/main/scripts/backend-service.sh
scp %m2% admin@172.31.249.254:/usr/local/newera/jar
scp %project% admin@172.31.249.254:/usr/local/newera/script
ssh admin@172.31.249.254 chmod 777 /usr/local/newera/script/backend-service.sh


