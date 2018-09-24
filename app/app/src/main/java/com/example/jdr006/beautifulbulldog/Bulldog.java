package com.example.jdr006.beautifulbulldog;

import java.io.Serializable;

public class Bulldog implements Serializable{
    private String name, age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
