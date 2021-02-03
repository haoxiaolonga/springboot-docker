package com.rabbitMq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author haoxl
 * @Title:
 * @Package
 * @Description:
 * @date 2021/2/3 15:32
 */
@Component
@Slf4j
public class MessageListener {

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = MessageConst.TEST_SEND_MESSAGE_QUEUE, durable = "true"),
            exchange = @Exchange(name = MessageConst.TEST_SEND_MESSAGE_EXCHANGE), key = MessageConst.DEFAULT_INDEX_CREATE_ROUTING_KEY))
    public void messageListener(String sendedMessage, Channel channel, Message message) {

        System.out.println("渠道：" + channel);
        System.out.println("hello --- 消息来了" + sendedMessage);
        log.info("真实传递的消息体为：{}", message);
    }

}
