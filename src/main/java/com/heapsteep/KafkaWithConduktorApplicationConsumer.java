package com.heapsteep;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaWithConduktorApplicationConsumer {

	public static void main(String[] args) {
		SpringApplication.run(KafkaWithConduktorApplicationConsumer.class, args);
		
		Properties properties=new Properties();
		
		//connect to Kafka Conducktor Playground
		properties.setProperty("bootstrap.servers", "cluster.playground.cdkt.io:9092");
		properties.setProperty("security.protocol", "SASL_SSL");
		properties.setProperty("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"prasanna\" password=\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2F1dGguY29uZHVrdG9yLmlvIiwic291cmNlQXBwbGljYXRpb24iOiJhZG1pbiIsInVzZXJNYWlsIjpudWxsLCJwYXlsb2FkIjp7InZhbGlkRm9yVXNlcm5hbWUiOiJwcmFzYW5uYSIsIm9yZ2FuaXphdGlvbklkIjo3MDQ2OSwidXNlcklkIjpudWxsLCJmb3JFeHBpcmF0aW9uQ2hlY2siOiJjYTE3OTkxYi1hNDNlLTQxNTktOTQyNS0yYjU3ZDhjMDZlNzEifX0.xUqONoItEtNqos1G6B0_yNoYKZin8vxhZ-ej-2RCdaI\";");		                                            
		properties.setProperty("sasl.mechanism", "PLAIN");
	
		//set consumer configs		
		properties.setProperty("key.deserializer", StringDeserializer.class.getName());
		properties.setProperty("value.deserializer", StringDeserializer.class.getName());
		
		properties.setProperty("group.id", "My Group Id 1");
		properties.setProperty("auto.offset.reset", "earliest");
		
		//create a consumer
		KafkaConsumer<String, String> consumer=new KafkaConsumer<>(properties);
		
		//subscribe to a topic
		consumer.subscribe(Arrays.asList("Heapsteep_second_topic"));
		
		// poll for data
        while (true) {

            System.out.println("Polling");

            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));

            for (ConsumerRecord<String, String> record: records) {
            	System.out.println("Key: " + record.key() + ", Value: " + record.value());
            	System.out.println("Partition: " + record.partition() + ", Offset: " + record.offset());
            }
        }
	}
}
