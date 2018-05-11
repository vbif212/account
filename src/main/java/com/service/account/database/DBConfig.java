package com.service.account.database;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

@Configuration
public class DBConfig {

    public @Bean
    MongoDbFactory mongoDbFactory() {
        return new SimpleMongoDbFactory(new MongoClient(), "accounts");
    }

    public @Bean
    MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoDbFactory());
    }

}
