package com.learn.start.test;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Component;


public class Cat extends Animal{
    @Autowired
    @Qualifier("eatFish")
    public Eat eat;
    @Override
    public void voice() {
        System.out.println(super.name + ":  meo meo");
        System.out.print(super.name + ": eat : ");
        eat.eat();
    }
    public Cat() {
        System.out.println("cat duoc tao ");
    }
    @PostConstruct
    public void PostConstruct(){
        System.out.println("postConstruct");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("destroy");
    }

    public Cat(String name) {
        super(name);

    }
}
