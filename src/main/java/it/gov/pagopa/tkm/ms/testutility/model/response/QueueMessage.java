package it.gov.pagopa.tkm.ms.testutility.model.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QueueMessage {

    private String plainMessage;

    private String encryptedMessage;

}
