package it.gov.pagopa.tkm.ms.testutility.controller.impl;

import it.gov.pagopa.tkm.ms.testutility.controller.SendToQueueController;
import it.gov.pagopa.tkm.ms.testutility.model.response.*;
import it.gov.pagopa.tkm.ms.testutility.service.*;
import lombok.extern.log4j.Log4j2;
import org.bouncycastle.openpgp.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
public class SendToQueueControllerImpl implements SendToQueueController {

    @Autowired
    private ProducerService producerService;

    @Override
    public QueueMessage sendToReadQueue(String message) throws PGPException {
        return producerService.sendToReadQueue(message);
    }

    @Override
    public QueueMessage sendToDeleteQueue(String message) {
        return producerService.sendToDeleteQueue(message);
    }

}
