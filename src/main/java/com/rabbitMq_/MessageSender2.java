package com.rabbitMq_;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.SocketUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author haoxl
 * @Title:
 * @Package
 * @Description:
 * @date 2021/5/7 17:23
 */

@Component
@Slf4j
public class MessageSender2 implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    private void init(){
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnCallback(this);
    }

    public static void main(String[] args) {

        int[] jobs = {1,2,3};
        List x = Arrays.asList(jobs);
        List y = Arrays.asList(1,2,3);
        List ym = Arrays.stream(jobs).boxed().collect(Collectors.toList());
        List z = CollectionUtils.arrayToList(jobs);
//        x.stream().forEach(t-> System.out.println(t));
//        y.stream().forEach(t-> System.out.println(t));
//        z.stream().forEach(t-> System.out.println(t));
        ym.stream().forEach(t-> System.out.println(t));
    }


    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        String id = correlationData != null ? correlationData.getId() : "";
        if (b) {
            System.out.println("消息确认成功," + id);
        } else {
            System.out.println("消息未成功投递,   cause:{}" + s);
        }
    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        log.info("消息被服务器退回。msg:{}, replyCode:{}. replyText:{}, exchange:{}, routingKey :{}",
                new String(message.getBody()), i, s, s1, s2);
    }

    public void send(String testSendMessageExchange, String defaultIndexCreateRoutingKey, String message) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(testSendMessageExchange,"no_key",message,correlationData);
        System.out.println("已经调用发送");
    }
}
