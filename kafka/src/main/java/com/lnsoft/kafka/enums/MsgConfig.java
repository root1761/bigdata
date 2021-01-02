package com.lnsoft.kafka.enums;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum MsgConfig {
    TOPIC1("192.168.42.133:9092,192.168.42.133:9093,192.168.42.133:9094","192.168.42.133:2181","topicName001");
    private String brokerList;
    private String zkList;
    private String topicName;

}
