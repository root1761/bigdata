package com.lnsoft.kafka.model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * @author Louyp
 * @version 1.0
 * @data 2020/06/23 9:43
 */
@Component
public class KafkaConsumer {
    Log log= LogFactory.getLog(KafkaConsumer.class);


    @KafkaListener(topics = "topic1",groupId = "group1")
    public void topicTest(ConsumerRecord<String,Object> consumerRecord, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC)String topic){
      log.info("key值为"+consumerRecord.key()+"value值为"+consumerRecord.value()+"offset值为"+consumerRecord.offset()+"ack的值为"+ack);
        
    }
}
