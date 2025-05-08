package com.learn.start.test;

public abstract  class Animal {
    protected String name;
    public abstract void voice() ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Animal(String name) {
        this.name = name;
    }

    public Animal() {
    }
}
