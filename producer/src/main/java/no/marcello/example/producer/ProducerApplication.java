package no.marcello.example.producer;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.jms.Queue;
import javax.jms.Topic;
import java.time.Instant;
import java.util.Arrays;

@SpringBootApplication
@EnableJms
@EnableScheduling
public class ProducerApplication {
    private static final Logger log = LoggerFactory.getLogger(ProducerApplication.class);

    @Value("${mq.url}")
    private String brokerUrl;

    private int messageCounter;

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(brokerUrl);
        connectionFactory.setExclusiveConsumer(true); // http://activemq.apache.org/exclusive-consumer.html
        return connectionFactory;
    }

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public Topic topic() {
        return new ActiveMQTopic("sample.topic");
    }

    @Scheduled(fixedDelay = 5000)
    public void performTask() {
        log.info("Performing task");

        this.jmsMessagingTemplate.convertAndSend(topic(), String.format("Greetings from ProducerApplication.java!  This is message #%d sent after startup.", ++messageCounter));
    }

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }
}
