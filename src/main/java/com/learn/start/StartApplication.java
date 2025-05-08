package com.learn.start;

import com.learn.start.test.Animal;

import com.learn.start.test.Cat;
import com.learn.start.test.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class StartApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(StartApplication.class, args);
//
//		Cat cat = context.getBean(Cat.class);
//		cat.setName("cat");
//		cat.voice();
//		context.close();
	}

}
