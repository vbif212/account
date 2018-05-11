package com.service.account.serviceLayer;

import com.service.account.database.DBConfig;
import com.service.account.database.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class ServiceLayer {
    private static ApplicationContext ctx = new AnnotationConfigApplicationContext(DBConfig.class);
    private static MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

    public static User getUserByLogin(String login){
        Query query = new Query(Criteria.where("login").is(login));
        return mongoOperation.findOne(query, User.class);
    }

    public static User getUserByEmail(String email) {
        Query query = new Query(Criteria.where("email").is(email));
        return mongoOperation.findOne(query, User.class);
    }

    public static User getUserByID(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoOperation.findOne(query, User.class);
    }

    public static void inputNewUserWithoutDevices(String login, String password, String email) {
        User user = new User(login, password, email);
        mongoOperation.insert(user);
    }

    public static void inputNewUser(String login, String password, String email, List<String> devices) {
        User user = new User(login, password, email, devices);
        mongoOperation.insert(user);
    }

    public static void deleteUser(String login) {
        Query query = new Query(Criteria.where("login").is(login));
        mongoOperation.findAndRemove(query, User.class);
    }
}
