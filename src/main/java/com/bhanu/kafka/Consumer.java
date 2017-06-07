package com.bhanu.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.security.auth.KafkaPrincipal;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by bhanu.prakash on 6/7/2017.
 * This is sample consumer
 */
public class Consumer {
    private static Logger logger = Logger.getLogger(Consumer.class.getSimpleName());

    public static void main(String[] args) {
        Consumer consumer = new Consumer();
        consumer.consumer();
    }

    /*
    Consumer, this will pull records form the topic it has subscribed to.

     */
    public void consumer(){
        logger.info("in consumer");
        String topic = "topic1";
        KafkaConsumer<String , String> consumer = null;
        Properties properties = new Properties();
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("consumer.properties");
            properties.load(inputStream);
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            consumer = new KafkaConsumer<String, String>(properties);

//            Subscribing to a given topic
            consumer.subscribe(Arrays.asList(topic));
            logger.info("List of topics "+consumer.listTopics());
            ConsumerRecord<String , String> record = null;
            while (true) {
                ConsumerRecords<String, String> consumerRecords = consumer.poll(1000);
                logger.info("Consumer Records count "+consumerRecords.count());
                for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                    logger.info("Consumer Record Key "+consumerRecord.key());
                    logger.info("Consumer Record Value "+consumerRecord.value());
                }
            }
        } catch (Exception e){

        }
        finally {

        }
    }
}
