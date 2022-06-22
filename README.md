# Test Utility
This microservice provides APIs for E2E testing of TKM.

## Configuration
The following ENVIRONMENT variables are needed to deploy and run the application.

**AZURE_KEYVAULT_CLIENT_ID**
**AZURE_KEYVAULT_CLIENT_KEY**
**AZURE_KEYVAULT_PROFILE**
**AZURE_KEYVAULT_TENANT_ID**
**AZURE_KEYVAULT_URI**
**ENABLE_KAFKA_APPENDER**
**KAFKA_APPENDER_BOOTSTRAP_SERVERS**
**KAFKA_CSTAR_BOOTSTRAP_SERVERS**
**KAFKA_APPENDER_SECURITY_PROTOCOL**
**KAFKA_APPENDER_TOPIC**
**KAFKA_DELETE_QUEUE_GROUP**
**KAFKA_DELETE_QUEUE_TOPIC**
**KAFKA_READ_QUEUE_GROUP**
**KAFKA_READ_QUEUE_TOPIC**
**KAFKA_SECURITY_PROTOCOL**
**KAFKA_SERVERS**
**KAFKA_WRITE_QUEUE_GROUP**
**KAFKA_WRITE_QUEUE_TOPIC**
**SERVER_PORT**

### Develop enviroment configuration
- Set **-Dspring.profiles.active=local** as jvm setting
- Add as enviroment variable **AZURE_KEYVAULT_CLIENT_ID=~~VALUE_TO_ADD~~;AZURE_KEYVAULT_CLIENT_KEY=~~VALUE_TO_ADD~~;AZURE_KEYVAULT_TENANT_ID=~~VALUE_TO_ADD~~;AZURE_KEYVAULT_URI=~~VALUE_TO_ADD~~**

## How to start SIT azure pipeline

1. Merge **feature branch** into **develop**<br>
   Pipeline starts automatically and do maven prepare release.<br>
   At the end, the pipeline create branch tmp/<version><br>

   If you have to do manually, run:<br>
   `$version=??` for poweshell or `version=??` for gitbash<br>
   `mvn --batch-mode release:clean release:prepare -DscmCommentPrefix="[skip ci]"`<br>
   `git push origin develop`<br>
   `git push origin --tags`<br>
   `git checkout -b tmp/${version} test-utility-${version}`<br>
   `git push --set-upstream origin tmp/${version}`<br>

2. Merge **tmp/${version}** into **release/sit**