package cn.czb.demo.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQUtil {

    private static Connection connection = null;

    static {
        ConnectionFactory factory = new ConnectionFactory();
        //设置rabbitmq服务器IP地址
        factory.setHost("192.168.239.133");
        //设置rabbitmq服务器连接端口
        factory.setPort(5672);
        //设置rabbitmq服务器虚拟主机
        factory.setVirtualHost("czb");
        //设置rabbitmq服务器用户名
        factory.setUsername("czb");
        //设置rabbitmq服务器密码
        factory.setPassword("czb");
        //如果解析ip地址超时  设置一个ip解析过程中的时间
        factory.setHandshakeTimeout(50000);
        //获取连接
        try {
            connection = factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        return  connection;
    }

}
