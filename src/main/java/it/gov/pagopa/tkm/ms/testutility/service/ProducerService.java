package it.gov.pagopa.tkm.ms.testutility.service;

import it.gov.pagopa.tkm.ms.testutility.model.response.*;
import org.bouncycastle.openpgp.*;

public interface ProducerService {

    QueueMessage sendToReadQueue(String message) throws PGPException;

    QueueMessage sendToDeleteQueue(String message) throws PGPException;

}
