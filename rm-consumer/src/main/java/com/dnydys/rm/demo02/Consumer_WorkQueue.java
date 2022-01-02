package com.dnydys.rm.demo02;

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
 * @Classname Consumer_WorkQueue
 * @Description TODO
 * @Date 2022/1/2 20:28
 * @Created by hasee
 */
public class Consumer_WorkQueue {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 1、获取连接
        ConnUtils cs = new ConnUtils();
        Connection connection = cs.getConnection();

        // 2、创建通道
        Channel channel = connection.createChannel();

        // 3、创建队列
        channel.queueDeclare("work_queue", true, false, false, null);

        // 4、接收消息
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("body：" + new String(body));
            }
        };

        // 5、监听消息
        channel.basicConsume("work_queue",true,consumer);
    }
}
