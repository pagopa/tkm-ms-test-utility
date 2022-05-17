package it.gov.pagopa.tkm.ms.testutility.controller;

import it.gov.pagopa.tkm.ms.testutility.model.response.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/read")
public interface ReadFromQueueController {

    @GetMapping("/write-queue")
    List<QueueMessage> readFromWriteQueue();

    @GetMapping("/delete-queue")
    List<QueueMessage> readFromDeleteQueue();

}
