package com.baizhi;

import com.lnsoft.kafka.enums.MsgConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaProducerTest {
    public static void main(String[] args) throws Exception{
        Properties props = new Properties();
        props.put("bootstrap.servers", MsgConfig.TOPIC1.getBrokerList());
        props.put("acks", "all");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer=null;
        try {
             producer= new KafkaProducer<>(props);
            for (int i = 0; i < 100; i++) {
                producer.send(new ProducerRecord<String, String>("topic003", Integer.toString(i), Integer.toString(i)));
                Thread.sleep(10000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }

    }
}
