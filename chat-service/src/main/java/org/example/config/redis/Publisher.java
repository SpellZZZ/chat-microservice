package org.example.config.redis;

import io.lettuce.core.RedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Publisher {
    RedisClient client;

    private static final Logger logger = LoggerFactory.getLogger(Publisher.class);

    public Publisher(){
        this.client = RedisClient.create("redis://localhost:6379");
    }

    public void publish(String channel, String message){
        logger.info("going to publish the message to channel {} and message = {}", channel, message);
        var connection = this.client.connect();
        connection.sync().publish(channel,message);
    }
}
