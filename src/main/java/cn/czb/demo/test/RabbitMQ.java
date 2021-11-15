package cn.czb.demo.test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQ {

        public static void rabbitMQ(String i) throws IOException, TimeoutException {
                //生产者  连接消息中间件发布消息
                //创建连接工厂
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
                Connection connection = factory.newConnection();
                //获取通道
                Channel channel = connection.createChannel();

                /**设置队列参数
                 * @param queue 队列名称  如果这个队列不存在，将会被创建
                 * @param durable 持久性：用来定义队列是否要持久化  true:持久化  false:不持久化
                 * @param exclusive 是否只能由创建者使用，其他连接不能使用。 true:独占队列  false:不独占队列
                 * @param autoDelete 是否自动删除（没有连接自动删除） true:自动删除   false:不自动删除
                 * @param arguments 队列的其他属性(构造参数)
                 */
                channel.queueDeclare("hello",true,false,true,null);

                /**发布消息
                 * @param exchange 消息交换机名称,空字符串将使用直接交换器模式，发送到默认的Exchange=amq.direct。此状态下，RoutingKey默认和Queue名称相同
                 * @param queueName 队列名称
                 * @param BasicProperties  设置消息持久化：MessageProperties.PERSISTENT_TEXT_PLAIN是持久化；MessageProperties.TEXT_PLAIN是非持久化。
                 * @param body 消息对象转换的byte[]
                 */
                channel.basicPublish("","javaQueues", MessageProperties.PERSISTENT_TEXT_PLAIN,("hello Word"+i).getBytes());

                //关闭连接
                channel.close();
                connection.close();
        }

         public static void main(String[] args) throws IOException, TimeoutException {
                for (Integer i=0;i<=10;i++){
                        rabbitMQ(i.toString());
                }
        }
}
