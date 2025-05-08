package com.learn.start.test;

import org.springframework.stereotype.Component;

@Component
public class Dog extends Animal{
    @Override
    public void voice() {
        System.out.println(super.name + ":  wao wao");
    }

    public Dog() {
    }

    public Dog(String name) {
        super(name);
    }
}
