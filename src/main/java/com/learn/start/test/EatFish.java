package com.learn.start.test;

import org.springframework.stereotype.Component;

@Component
public class EatFish implements Eat{
    public void eat() {
        System.out.print("EatFish");
    }
}
