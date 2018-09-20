package no.marcello.example.persister;

import no.marcello.example.persister.repository.DataRepository;
import no.marcello.example.persister.model.Data;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableJms
public class PersisterApplication {
    private static final Logger log = LoggerFactory.getLogger(PersisterApplication.class);

    @Value("${mq.url}")
    private String brokerUrl;

    @Autowired
    private DataRepository dataRepository;

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(brokerUrl);
        connectionFactory.setExclusiveConsumer(true); // http://activemq.apache.org/exclusive-consumer.html
        return connectionFactory;
    }

    @Bean(name = "topicListenerFactory")
    public JmsListenerContainerFactory jmsListenerContainerTopic() {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setPubSubDomain(true);
        bean.setConnectionFactory(connectionFactory());
        return bean;
    }

    @JmsListener(destination = "sample.topic", containerFactory = "topicListenerFactory")
    public void receiveTopic(String text) {
        log.info("Consumed message with the following content: {}", text);

        Data data = new Data();

        data.setValue(text);

        dataRepository.save(data);
    }

    @PostConstruct
    public void init() {
        dataRepository.deleteAll();
    }

    public static void main(String[] args) {
        SpringApplication.run(PersisterApplication.class, args);
    }
}
