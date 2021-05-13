package cc.paukner.sfgjms.listener;

import cc.paukner.sfgjms.config.JmsConfig;
import cc.paukner.sfgjms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class HelloMessageListener {

    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.MY_QUEUE)
    public void listen(@Payload HelloWorldMessage helloWorldMessage,
                       @Headers MessageHeaders headers, // could be omitted, but interesting to inspect
                       Message message) {               // could be omitted, but interesting to inspect
//        System.out.println("Message received from queue:");
//        System.out.println(helloWorldMessage);
        // you could throw an exception here to notice that the message would be redelivered
    }

    @JmsListener(destination = JmsConfig.MY_TXRX_QUEUE)
    public void listenAndReply(@Payload HelloWorldMessage helloWorldMessage,
                       @Headers MessageHeaders headers,
                       Message message) throws JMSException {
        HelloWorldMessage reply = HelloWorldMessage.builder()
                .id(UUID.randomUUID())
                .message("QSL")
                .build();
        // reply using temporary queue for that specific message
        jmsTemplate.convertAndSend(message.getJMSReplyTo(), reply);
    }
}
