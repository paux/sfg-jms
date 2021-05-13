package cc.paukner.sfgjms.listener;

import cc.paukner.sfgjms.config.JmsConfig;
import cc.paukner.sfgjms.model.HelloWorldMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.Message;

@Component
public class HelloMessageListener {

    @JmsListener(destination = JmsConfig.MY_QUEUE)
    public void listen(@Payload HelloWorldMessage helloWorldMessage,
                       @Headers MessageHeaders headers, // could be omitted, but interesting to inspect
                       Message message) {               // could be omitted, but interesting to inspect
        System.out.println("Message received from queue:");
        System.out.println(helloWorldMessage);
    }
}
