package com.rabbitMq_;

import com.rabbitmq.client.Channel;
import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Argument;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.util.SocketUtils;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ThreadPoolExecutor;

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

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = MessageConst.TEST_SEND_MESSAGE_QUEUE, durable = "true",
            arguments = {@Argument(name = "x-dead-letter-routing-key", value = MessageConst.DEFAULT_INDEX_CREATE_ROUTING_KEY),
                    @Argument(name = "x-dead-letter-exchange", value = "doc.exchange.name")}),
            exchange = @Exchange(name = MessageConst.TEST_SEND_MESSAGE_EXCHANGE),
            key = MessageConst.DEFAULT_INDEX_CREATE_ROUTING_KEY))
    public void messageListener(String sendedMessage, Channel channel, Message message) throws IOException {

        System.out.println("渠道：" + channel);
        System.out.println("hello --- 消息来了" + sendedMessage);
        log.info("真实传递的消息体为：{}", message);
        // 正常ACK操作
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

        // 丢入死信队列
        /*消息被否定确认，使用 channel.basicNack 或 channel.basicReject ，并且此时requeue 属性被设置为false。
        消息在队列的存活时间超过设置的TTL时间。
        消息队列的消息数量已经超过最大队列长度。
        那么该消息将成为“死信”。
        “死信”消息会被RabbitMQ进行特殊处理，如果配置了死信队列信息，那么该消息将会被丢进死信队列中，如果没有配置，则该消息将会被丢弃。
        */
        channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
    }


    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = MessageConst.TEST_SEND_MESSAGE_QUEUE, durable = "true"),
            exchange = @Exchange(name = MessageConst.TEST_SEND_MESSAGE_EXCHANGE),
            key = MessageConst.DEFAULT_INDEX_CREATE_ROUTING_KEY))
    public void messageListenerBak(String sendedMessage, Channel channel, Message message) throws IOException {

        System.out.println("渠道：" + channel);
        System.out.println("hello 我是第二个队列 --- 消息来了" + sendedMessage);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }


    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "dead.queue", durable = "true"),
            exchange = @Exchange(name = "doc.exchange.name"), key = MessageConst.DEFAULT_INDEX_CREATE_ROUTING_KEY))
    public void deadMessageListener(String sendedMessage, Channel channel, Message message) throws IOException {

        System.out.println("死信队列来了啊！！！！-------------------");
        log.info("死信队列");
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }


    public static void main(String[] args) throws InterruptedException {
        Integer a= 100;
        Integer b= new Integer(100);
        System.out.println(a==b);

        Integer a1= -129;
        Integer b1= -129;
        System.out.println(a1==b1);

        Integer A = 128;
        Integer B = 128;
        System.out.println(A == B);

        HashMap h = new HashMap(2);
        h.put("x","x");
        /**
         * required
         * new
         * nested
         * never
         * mandity
         * not_supported
         * support
         *
         */

    }
}

