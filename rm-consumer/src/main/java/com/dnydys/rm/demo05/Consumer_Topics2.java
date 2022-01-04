package com.dnydys.rm.demo05;

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
 * @Classname Consumer_Topics2
 * @Description TODO
 * @Date 2022/1/2 21:29
 * @Created by hasee
 */
public class Consumer_Topics2 {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 1、获取连接
        ConnUtils cs = new ConnUtils();
        Connection connection = cs. getConnection();

        // 2、创建通道
        Channel channel = connection.createChannel();

        // 3、接受消息
        String queue2Name = "test_topic_queue2";
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("body2："+new String(body));
            }
        };
        // 4、监听消息
        channel.basicConsume(queue2Name,true,consumer);
    }
}
