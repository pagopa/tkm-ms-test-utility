package it.gov.pagopa.tkm.ms.testutility.service;

import it.gov.pagopa.tkm.ms.testutility.model.response.*;
import org.bouncycastle.openpgp.*;

import java.util.*;

public interface ConsumerService {

    List<QueueMessage> readFromWriteQueue() throws PGPException;

    List<QueueMessage> readFromDeleteQueue() throws PGPException;

}
