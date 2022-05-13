package it.gov.pagopa.tkm.ms.testutility.service.impl;

import it.gov.pagopa.tkm.ms.testutility.model.response.*;
import it.gov.pagopa.tkm.ms.testutility.service.*;
import it.gov.pagopa.tkm.service.*;
import lombok.extern.log4j.*;
import org.bouncycastle.openpgp.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.kafka.core.*;
import org.springframework.stereotype.*;

@Service
@Log4j2
public final class ProducerServiceImpl implements ProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.topics.read-queue.name}")
    private String readQueueTopic;
    @Value("${spring.kafka.topics.delete-queue.name}")
    private String deleteQueueTopic;
    @Value("${keyvault.readQueuePubPgpKey}")
    private String pgpPublicKey;

    @Override
    public QueueMessage sendToReadQueue(String message) throws PGPException {
        String encryptedMessage = PgpStaticUtils.encrypt(message, pgpPublicKey);
        log.info("Plain message: " + message + " - Encrypted message: " + encryptedMessage);
        kafkaTemplate.send(readQueueTopic, encryptedMessage);
        log.info("Message successfully encrypted and written on read queue");
        return new QueueMessage(message, encryptedMessage);
    }

    @Override
    public QueueMessage sendToDeleteQueue(String message) throws PGPException {
        String encryptedMessage = PgpStaticUtils.encrypt(message, pgpPublicKey);
        log.info("Plain message: " + message + " - Encrypted message: " + encryptedMessage);
        kafkaTemplate.send(deleteQueueTopic, encryptedMessage);
        log.info("Message successfully encrypted and written on delete queue");
        return new QueueMessage(message, encryptedMessage);
    }

}