﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using RabbitMQ.Client;

namespace MOMSender
{
    class Producer
    {
        static void Main(string[] args)
        {

            var factory = new ConnectionFactory() { HostName = "localhost" };
            using (var connection = factory.CreateConnection())
            using (var channel = connection.CreateModel()) 
            { 
                channel.QueueDeclare(queue: "queue1", durable: false, exclusive: false, autoDelete: false, arguments: null);
              
                string message = "Hello World!"; var body = Encoding.UTF8.GetBytes(message);
                
                channel.BasicPublish(exchange: "", routingKey: "queue1", basicProperties: null, body: body); Console.WriteLine(" [x] Sent {0}", message);
            }
            Console.WriteLine(" Press [enter] to exit."); Console.ReadLine();
        }
    }
}