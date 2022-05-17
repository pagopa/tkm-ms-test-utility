package it.gov.pagopa.tkm.ms.testutility.controller;

import it.gov.pagopa.tkm.ms.testutility.model.response.*;
import org.bouncycastle.openpgp.*;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/send")
public interface SendToQueueController {

    @PostMapping("/read-queue")
    QueueMessage sendToReadQueue(@RequestBody String message) throws PGPException;

    @PostMapping("/delete-queue")
    QueueMessage sendToDeleteQueue(@RequestBody String message) throws PGPException;

}
