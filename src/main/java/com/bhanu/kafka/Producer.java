package com.bhanu.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by bhanu.prakash on 6/6/2017.
 * Simple Producer. This will be producing records and insert into Kafka.
 */
public class Producer {
    private static Logger logger = Logger.getLogger(Producer.class.getCanonicalName());
    public static void main(String[] args) {
        Producer producer = new Producer();
        producer.producer();
    }

//    producer
    public void producer(){
        logger.info("In Producer");
        KafkaProducer<String , String> kafkaProducer = null;
        Properties properties = new Properties();
//        reading producer properties file.
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("producer.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        kafkaProducer = new KafkaProducer<String , String>(properties);
        ProducerRecord<String , String> producerRecord = new ProducerRecord<String, String>("topic1" , "Hello1");
        try {

        kafkaProducer.send(producerRecord);
        kafkaProducer.flush();
        logger.info("Message Send Successfully");
        } catch (Exception exception){
            exception.printStackTrace();
        } finally {
            try {
                kafkaProducer.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
