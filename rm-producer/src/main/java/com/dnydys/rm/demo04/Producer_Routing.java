package com.dnydys.rm.demo04;

import com.dnydys.rm.utils.ConnUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Classname Producer_Routing
 * @Description TODO
 * @Date 2022/1/2 21:02
 * @Created by hasee
 */
public class Producer_Routing {

    public static void main(String[] args) throws IOException, TimeoutException {
        // 1、获取连接
        ConnUtils cs = new ConnUtils();
        Connection connection = cs.getConnection();

        //2、创建通道
        Channel channel = connection.createChannel();

        // 3、创建交换机
        String exchangeName = "test_direct";
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT,true,false,false,null);

        // 4、创建队列
        String queue1Name = "test_direct_queue1";
        String queue2Name = "test_direct_queue2";
        channel.queueDeclare(queue1Name,true,false,false,null);
        channel.queueDeclare(queue2Name,true,false,false,null);

        // 5、绑定队列和交换机
        // 队列一绑定 t1
        channel.queueBind(queue1Name,exchangeName,"t1");
        // 队列二绑定 t2和t3
        channel.queueBind(queue2Name,exchangeName,"t2");
        channel.queueBind(queue2Name,exchangeName,"t3");
        // 6、发送消息
        String body = "test_direct";
        String body2 = "test_direct1";
        String body3 = "test_direct2";
        channel.basicPublish(exchangeName,"t1",null,body.getBytes());

        channel.basicPublish(exchangeName,"t2",null,body2.getBytes());
        channel.basicPublish(exchangeName,"t3",null,body3.getBytes());

        // 7、释放资源
        channel.close();
        connection.close();
    }
}
