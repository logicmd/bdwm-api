mvn clean package
#java -jar target\bdwm-api-1.0-SNAPSHOT.jar 
mvn exec:java -Dexec.mainClass="us.hk.bdwm.api.Application" -Denv=debug