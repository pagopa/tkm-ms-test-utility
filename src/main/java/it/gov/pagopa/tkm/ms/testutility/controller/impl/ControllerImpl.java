package it.gov.pagopa.tkm.ms.testutility.controller.impl;

import it.gov.pagopa.tkm.ms.testutility.controller.Controller;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
public class ControllerImpl implements Controller {

    @Value("${spring.kafka.topics.write-queue.jaas.config.producer}")
    private String writeQueueTopic;

    @GetMapping
    public void ciao() {
        log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        log.info(writeQueueTopic);
        log.info("END !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

}
