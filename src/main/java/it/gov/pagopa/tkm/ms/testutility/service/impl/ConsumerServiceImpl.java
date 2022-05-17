package it.gov.pagopa.tkm.ms.testutility.service.impl;

import it.gov.pagopa.tkm.ms.testutility.model.response.*;
import it.gov.pagopa.tkm.ms.testutility.service.*;
import it.gov.pagopa.tkm.service.*;
import lombok.extern.log4j.*;
import org.apache.kafka.clients.consumer.*;
import org.bouncycastle.openpgp.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.util.*;

@Service
@Log4j2
public final class ConsumerServiceImpl implements ConsumerService {

    @Value("${spring.kafka.topics.write-queue.name}")
    private String writeQueueTopic;

    @Value("${spring.kafka.topics.delete-queue.name}")
    private String deleteQueueTopic;

    @Value("${keyvault.tkmReadTokenParPanPvtPgpKey}")
    private String privatePgpKey;

    @Value("${keyvault.tkmReadTokenParPanPvtPgpKeyPassphrase}")
    private String pgpPassphrase;

    @Autowired
    @Qualifier("writeConsumer")
    private Consumer<String, String> writeConsumer;

    @Autowired
    @Qualifier("deleteConsumer")
    private Consumer<String, String> deleteConsumer;

    @Override
    public List<QueueMessage> readFromWriteQueue() {
        return readFromQueue(writeConsumer, writeQueueTopic);
    }

    @Override
    public List<QueueMessage> readFromDeleteQueue() {
        return readFromQueue(deleteConsumer, deleteQueueTopic);
    }

    private List<QueueMessage> readFromQueue(Consumer<String, String> consumer, String topic) {
        List<QueueMessage> lastMessages = new ArrayList<>();
        try {
            consumer.subscribe(Collections.singletonList(topic));
            consumer.poll(Duration.ofSeconds(1)).forEach(
                r -> {
                    try {
                        String encryptedMessage = r.value();
                        String message = PgpStaticUtils.decrypt(encryptedMessage, privatePgpKey, pgpPassphrase);
                        log.info("Plain message: " + message + " - Encrypted message: " + encryptedMessage);
                        lastMessages.add(new QueueMessage(message, encryptedMessage));
                    } catch (PGPException e) {
                        throw new RuntimeException(e);
                    }
                }
            );
            consumer.commitSync();
        } finally {
            consumer.unsubscribe();
        }
        return lastMessages;
    }

}