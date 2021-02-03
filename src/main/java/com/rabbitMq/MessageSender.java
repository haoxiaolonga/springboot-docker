package com.rabbitMq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author haoxl
 * @Title:
 * @Package
 * @Description:
 * @date 2021/2/3 15:18
 */
@RestController
@RequestMapping("/sendMq")
public class MessageSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @PostMapping("/send")
    public String sendMq(@RequestBody String message) {

        // String exchange, String routingKey, Object message
        amqpTemplate.convertAndSend(MessageConst.TEST_SEND_MESSAGE_EXCHANGE, MessageConst.DEFAULT_INDEX_CREATE_ROUTING_KEY, message);
        return "发送成功";
    }
}
