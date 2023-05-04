package com.heapsteep;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaWithConduktorApplicationProducer {

	public static void main(String[] args) {
		SpringApplication.run(KafkaWithConduktorApplicationProducer.class, args);
		
		Properties properties=new Properties();
		
		// set producer properties
		properties.setProperty("bootstrap.servers", "cluster.playground.cdkt.io:9092");
		properties.setProperty("security.protocol", "SASL_SSL");
		properties.setProperty("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"prasanna\" password=\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2F1dGguY29uZHVrdG9yLmlvIiwic291cmNlQXBwbGljYXRpb24iOiJhZG1pbiIsInVzZXJNYWlsIjpudWxsLCJwYXlsb2FkIjp7InZhbGlkRm9yVXNlcm5hbWUiOiJwcmFzYW5uYSIsIm9yZ2FuaXphdGlvbklkIjo3MDQ2OSwidXNlcklkIjpudWxsLCJmb3JFeHBpcmF0aW9uQ2hlY2siOiJjYTE3OTkxYi1hNDNlLTQxNTktOTQyNS0yYjU3ZDhjMDZlNzEifX0.xUqONoItEtNqos1G6B0_yNoYKZin8vxhZ-ej-2RCdaI\";");		                                            
		properties.setProperty("sasl.mechanism", "PLAIN");
	
		properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());
		
        // create the Producer
		KafkaProducer<String, String> producer=new KafkaProducer<>(properties);
		
		// create a Producer Record
		ProducerRecord<String, String> producerRecord=new ProducerRecord<>("Heapsteep_second_topic","key 24", "Message 24");
								
		// send data
		producer.send(producerRecord);
		
		// flush and close the producer
		producer.flush();
		
		producer.close();
	}
}
