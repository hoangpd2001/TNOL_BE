package com.learn.start.test;

import org.springframework.stereotype.Component;

@Component
public class EatMeat implements Eat{
    public void eat() {
        System.out.println("EatMeat");
    }
}
