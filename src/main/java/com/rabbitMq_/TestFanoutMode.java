package com.rabbitMq_;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author haoxl
 * @Title:
 * @Package
 * @Description:
 * @date 2021/4/13 14:48
 */
@Component
public class TestFanoutMode {


    /*@RabbitListener(bindings = @QueueBinding(value = @Queue(name = "test.fanout.queue1", durable = "true"),
            exchange = @Exchange(name = "test.fanout.send.message.exchange")))
    public void messageListenerBak(String sendedMessage, Channel channel, Message message) throws IOException {

        System.out.println("测试fanout模式第一种：" + channel);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }*/

    /*@RabbitListener(bindings = @QueueBinding(value = @Queue(name = "test.fanout.queue2", durable = "true"),
            exchange = @Exchange(name = "test.fanout.send.message.exchange")))
    public void messageListener2(String sendedMessage, Channel channel, Message message) throws IOException {

        System.out.println("测试fanout模式第2个队列：" + channel);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }*/

}
