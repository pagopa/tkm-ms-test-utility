package it.gov.pagopa.tkm.ms.testutility.controller.impl;

import it.gov.pagopa.tkm.ms.testutility.controller.*;
import it.gov.pagopa.tkm.ms.testutility.model.response.*;
import it.gov.pagopa.tkm.ms.testutility.service.*;
import lombok.extern.log4j.*;
import org.bouncycastle.openpgp.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Log4j2
@RestController
public class ReadFromQueueControllerImpl implements ReadFromQueueController {

    @Autowired
    private ConsumerService consumerService;

    @Override
    public List<QueueMessage> readFromWriteQueue() throws PGPException {
        return consumerService.readFromWriteQueue();
    }

    @Override
    public List<QueueMessage> readFromDeleteQueue() throws PGPException {
        return consumerService.readFromDeleteQueue();
    }

}
