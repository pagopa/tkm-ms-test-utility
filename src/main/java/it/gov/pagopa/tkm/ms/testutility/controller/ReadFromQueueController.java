package it.gov.pagopa.tkm.ms.testutility.controller;

import it.gov.pagopa.tkm.ms.testutility.model.response.*;
import org.bouncycastle.openpgp.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/read")
public interface ReadFromQueueController {

    @GetMapping("/write-queue")
    List<QueueMessage> readFromWriteQueue() throws PGPException;

    @GetMapping("/delete-queue")
    List<QueueMessage> readFromDeleteQueue() throws PGPException;

}
