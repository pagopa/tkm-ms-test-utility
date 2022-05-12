package it.gov.pagopa.tkm.ms.testutility.controller.impl;

import it.gov.pagopa.tkm.ms.testutility.controller.Controller;
import it.gov.pagopa.tkm.ms.testutility.service.*;
import lombok.extern.log4j.Log4j2;
import org.bouncycastle.openpgp.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
public class ControllerImpl implements Controller {

    @Autowired
    private ProducerService producerService;

    @GetMapping
    public void ciao() throws PGPException {
        producerService.sendMessage("ciao");
    }

}
