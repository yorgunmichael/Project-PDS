m2=${M2_REPO}
clsspath=src/main/resources
clsspath+=${clsspath}:${m2}/episen/backend-service/1.0-SNAPSHOT/backend-service-1.0-SNAPSHOT-jar-with-dependencies.jar
exec java -cp ${clsspath} episen.backend.server.BackendService $*
