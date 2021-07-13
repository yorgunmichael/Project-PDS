set m2=%M2_REPO%
set clsspth=%m2%/episen/backend-service/1.0-SNAPSHOT/backend-service-1.0-SNAPSHOT-jar-with-dependencies.jar
call java -cp %clsspth% episen.backend.server.BackendService %*