package com.dnydys.rm.demo;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Classname ConnUtils
 * @Description TODO
 * @Date 2022/1/2 17:15
 * @Created by hasee
 */
public class ConnUtils {

    public Connection getConnection() throws IOException, TimeoutException {
        // 1、创建工厂连接
        ConnectionFactory connectionFactory = new ConnectionFactory();

        //2、设置参数
        // ip
        connectionFactory.setHost("000.000.000.000");
        //port 默认：5672
        connectionFactory.setPort(5672);
        //虚拟机设置 默认：/
        connectionFactory.setVirtualHost("/");
        //用户名 默认guest
        connectionFactory.setUsername("guest");
        //密码 默认guest
        connectionFactory.setPassword("guest");

        //3、创建连接
        Connection connection = connectionFactory.newConnection();
        return connection;
    }
}
