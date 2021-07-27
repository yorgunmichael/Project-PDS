set m2=C:/Users/yorgu/.m2/repository/episen/backend-service/1.0-SNAPSHOT/backend-service-1.0-SNAPSHOT-jar-with-dependencies.jar
set project=C:/Users/yorgu/IdeaProjects/Project-PDS/backend-service/src/main/scripts/backend-service.sh
scp %m2% tata@172.31.249.254:/home/tata/tests/
scp %project% tata@172.31.249.254:/home/tata/tests/
ssh tata@172.31.249.254 chmod 777 /home/tata/tests/backend-service.sh


