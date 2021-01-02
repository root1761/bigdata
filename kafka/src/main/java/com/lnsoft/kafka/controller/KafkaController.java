package com.lnsoft.kafka.controller;

import com.lnsoft.kafka.model.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

/**
 * @author Louyp
 * @version 1.0
 * @data 2020/06/23 9:40
 */
@RestController
@RequestMapping("/kafkaOperation")
public class KafkaController {
    @Autowired
    private KafkaProducer kafkaProducer;

@RequestMapping("/send")
@CrossOrigin
    public void send(){
    kafkaProducer.send("abcd12345");
}
}
