package it.gov.pagopa.tkm.ms.testutility.service;

import it.gov.pagopa.tkm.ms.testutility.model.response.*;

import java.util.*;

public interface ConsumerService {

    List<QueueMessage> readFromWriteQueue();

    List<QueueMessage> readFromDeleteQueue();

}
