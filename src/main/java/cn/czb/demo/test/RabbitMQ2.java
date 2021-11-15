package cn.czb.demo.test;

import cn.czb.demo.util.RabbitMQUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

public class RabbitMQ2 {
    public static void main(String[] args) throws Exception {


        //获取连接
        Connection connection = RabbitMQUtil.getConnection();
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

        /**消费者消费消息
         * @param queue 队列名称
         * @param autoAck 是否自动应答。false表示consumer在成功消费过后必须要手动回复一下服务器，如果不回复，服务器就将认为此条消息消费失败，继续分发给其他consumer。
         * @param callback 回调方法类，一般为自己的Consumer类
         */
        channel.basicConsume("javaQueues",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                java.lang.String message = new String(body);
                System.out.println("消费者获取消息： "+message);
            }
        });
    }
}
