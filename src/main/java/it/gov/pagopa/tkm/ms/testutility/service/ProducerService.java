package it.gov.pagopa.tkm.ms.testutility.service;

import org.bouncycastle.openpgp.*;

public interface ProducerService {

    void sendMessage(String message) throws PGPException;

}
