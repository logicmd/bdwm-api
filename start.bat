mvn clean package
::java -jar target\bdwm-api.jar 
mvn exec:java -Dexec.mainClass="us.hk.bdwm.api.WebServer" -Denv=debug
