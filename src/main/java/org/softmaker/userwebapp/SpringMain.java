package org.softmaker.userwebapp;

import org.softmaker.userwebapp.model.Role;
import org.softmaker.userwebapp.model.User;
import org.softmaker.userwebapp.web.user.AdminRestController;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        System.out.println("Beans: " + Arrays.toString(applicationContext.getBeanDefinitionNames()));
        AdminRestController adminRestController = applicationContext.getBean(AdminRestController.class);
        adminRestController.create(new User(null, "Name", "email@email.com", "password", Role.ADMIN));
        System.out.println(adminRestController.getAll());
    }
}
