/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rabbitmq;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;

/**
 *
 * @author Admin
 */
public class Consumer {
    
    
    // the name of the queue to be used to communicate    
    private final static String QUEUE_NAME = "queue1";
    
    public static void main(String[] argv) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        
        factory.setHost("localhost");
        
        Connection connection = factory.newConnection();
        
        Channel channel = connection.createChannel();
        
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        
        QueueingConsumer consumer = new QueueingConsumer(channel);
        
        channel.basicConsume(QUEUE_NAME, true, consumer);
        
        while(true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        }
    }
    
    
}