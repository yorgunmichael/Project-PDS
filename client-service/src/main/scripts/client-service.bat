set m2=%M2_REPO%
set clsspth=%m2%/episen/client-service/1.0-SNAPSHOT/client-service-1.0-SNAPSHOT-jar-with-dependencies.jar
call java -cp %clsspth% client.Client %*