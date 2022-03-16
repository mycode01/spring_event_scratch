package com.example.demo.eventhandling;

import com.example.demo.eventhandling.persistence.User;
import com.example.demo.eventhandling.service.UserRegister;
import com.example.demo.eventhandling.service.UserVisitor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@Slf4j
@EnableAsync
@EnableAspectJAutoProxy(proxyTargetClass = true) // use interface
@SpringBootApplication
public class EventHandlingApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EventHandlingApplication.class, args);
	}

	@Autowired
	UserRegister userRegister;

	@Autowired
	UserVisitor userVisitor;

	@Override
	public void run(String... args) throws Exception {
		User user = userRegister.register("김번개");

		log.info(user.toString());

		User user2 = userVisitor.visit("김번개");
		log.info(user2.toString());
	}
}
