package com.baizhi;



import com.lnsoft.kafka.enums.MsgConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;


public class KafkaConsumerTest {
    public static void main(String[] args) {
        Properties prop=new Properties();
        prop.put("bootstrap.servers", MsgConfig.TOPIC1.getBrokerList());
        prop.put("group.id","test");
        prop.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        prop.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        //创建kafka消费者
        KafkaConsumer kafkaConsumer = new KafkaConsumer<>(prop);
        //指定消费的topic
        kafkaConsumer.subscribe(Arrays.asList("topic2"));

        try {
            while(true){
            ConsumerRecords<String,String> consumerRecords = kafkaConsumer.poll(1000);
                for (ConsumerRecord<String,String> consumerRecord : consumerRecords) {
                    System.out.println("offset"+consumerRecord.offset());
                    System.out.println("key"+consumerRecord.key());
                    System.out.println("value"+consumerRecord.value());
                    System.out.println("");
                }
                Thread.sleep(5000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            kafkaConsumer.close();
        }


    }
}
