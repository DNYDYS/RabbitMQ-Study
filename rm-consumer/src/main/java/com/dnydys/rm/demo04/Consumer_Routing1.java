package com.dnydys.rm.demo04;

import com.dnydys.rm.utils.ConnUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Classname Consumer_Routing1
 * @Description TODO
 * @Date 2022/1/2 21:07
 * @Created by hasee
 */
public class Consumer_Routing1 {

    public static void main(String[] args) throws IOException, TimeoutException {
        // 1、创建连接
        ConnUtils cs = new ConnUtils();
        Connection connection = cs.getConnection();

        // 2、创建通道
        Channel channel = connection.createChannel();

        // 3、创建队列
        String queue1Name = "test_direct_queue1";

        // 4、接受消息
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("body1：" + new String(body));
            }
        };

        // 5、监听消息
        channel.basicConsume(queue1Name,true,consumer);
    }
}
