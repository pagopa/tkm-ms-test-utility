package it.gov.pagopa.tkm.ms.testutility.config;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.config.SaslConfigs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@Configuration
@EnableKafka
public class KafkaConfiguration {

    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String kafkaBootstrapServer;

    @Value("${spring.kafka.consumer.bootstrap-servers-cstar}")
    private String kafkaBootstrapServerCstar;

    @Value("${spring.kafka.consumer.properties.security.protocol}")
    private String consumerSecurityProtocol;

    @Value("${spring.kafka.consumer.properties.sasl.mechanism}")
    private String consumerSaslMechanism;

    @Value("${spring.kafka.consumer.key-deserializer}")
    private String keyDeserializer;

    @Value("${spring.kafka.consumer.value-deserializer}")
    private String valueDeserializer;

    @Value("${spring.kafka.producer.key-serializer}")
    private String keySerializer;

    @Value("${spring.kafka.producer.value-serializer}")
    private String valueSerializer;

    @Value("${spring.kafka.consumer.enable-auto-commit}")
    private String consumerEnableAutoCommit;

    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String autoOffsetReset;

    @Value("${spring.kafka.consumer.max-poll-records}")
    private String maxPollRecords;

    @Value("${spring.kafka.producer.properties.security.protocol}")
    private String producerSecurityProtocol;

    @Value("${spring.kafka.producer.properties.sasl.mechanism}")
    private String producerSaslMechanism;

    @Value("${spring.kafka.topics.write-queue.client-id}")
    private String writeClientId;

    @Value("${spring.kafka.topics.read-queue.client-id}")
    private String readClientId;

    @Value("${spring.kafka.topics.delete-queue.client-id}")
    private String deleteClientId;

    @Value("${spring.kafka.topics.write-queue.sasl.jaas.config.producer}")
    private String writeJaasConfig;

    @Value("${spring.kafka.topics.delete-queue.sasl.jaas.config.consumer}")
    private String deleteJaasConfigConsumer;

    @Value("${spring.kafka.topics.read-queue.sasl.jaas.config.consumer}")
    private String readJaasConfig;

    @Value("${spring.kafka.topics.write-queue.group-id}")
    private String writeConsumerGroup;

    @Value("${spring.kafka.topics.delete-queue.group-id}")
    private String deleteConsumerGroup;

    @Value("${spring.kafka.topics.read-queue.group-id}")
    private String readGroup;

    @Bean
    public Consumer<String, String> writeConsumer() {
        return new KafkaConsumer<>(getConfigs(writeClientId, writeJaasConfig, writeConsumerGroup, kafkaBootstrapServerCstar, true));
    }

    @Bean
    public Consumer<String, String> deleteConsumer() {
        return new KafkaConsumer<>(getConfigs(deleteClientId, deleteJaasConfigConsumer, deleteConsumerGroup, kafkaBootstrapServer, true));
    }

    @Bean(name = "readProducer")
    public KafkaProducer<String, String> readProducer() {
        return new KafkaProducer<>(getConfigs(readClientId, readJaasConfig, readGroup, kafkaBootstrapServer, false));
    }

    @Bean(name = "deleteProducer")
    public KafkaProducer<String, String> deleteProducer() {
        return new KafkaProducer<>(getConfigs(deleteClientId, deleteJaasConfigConsumer, deleteConsumerGroup, kafkaBootstrapServer, false));
    }

    private Map<String, Object> getConfigs(String clientId, String jaas, String groupId, String server, boolean consumer) {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, server);
        if (consumer) {
            configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializer);
            configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDeserializer);
        } else {
            configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
            configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
        }
        configProps.put("security.protocol",
                consumer ? consumerSecurityProtocol : producerSecurityProtocol);
        configProps.put(SaslConfigs.SASL_MECHANISM,
                consumer ? consumerSaslMechanism : producerSaslMechanism);
        configProps.put(ProducerConfig.CLIENT_ID_CONFIG, clientId);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
        if (StringUtils.isNotBlank(jaas)) {
            configProps.put(SaslConfigs.SASL_JAAS_CONFIG, jaas);
        }
        return configProps;
    }

}

