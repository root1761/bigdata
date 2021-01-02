package com.lnsoft.kafka.model;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @author Louyp
 * @version 1.0
 * @data 2020/06/23 10:01
 */
@Component
public class KafkaProducer {
@Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;
public void  send(Object obj){
    final ListenableFuture<SendResult<String, Object>> topic1 = kafkaTemplate.send("topic1", obj);
    topic1.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
        @Override
        public void onFailure(Throwable throwable) {
            System.out.println("消息发送失败");
        }

        @Override
        public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
            System.out.println("消息发送成功"+stringObjectSendResult.toString());

        }
    });

}
}
