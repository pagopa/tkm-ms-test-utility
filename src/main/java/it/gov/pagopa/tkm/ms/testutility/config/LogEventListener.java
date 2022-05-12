package it.gov.pagopa.tkm.ms.testutility.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class LogEventListener {

    @EventListener
    public void onStartup(ApplicationReadyEvent event) {
        String enableKafkaAppender = System.getenv("ENABLE_KAFKA_APPENDER");
        String azureKeyvaultProfile = System.getenv("AZURE_KEYVAULT_PROFILE");
        String kafkaAppenderTopic = System.getenv("KAFKA_APPENDER_TOPIC");
        log.info(String.format("Started Test Utility with KEY VAULT profile '%s' and KAFKA APPENDER '%s' on topic '%s'",
                azureKeyvaultProfile, enableKafkaAppender, kafkaAppenderTopic));
    }
}
