package com.example.jdr006.beautifulbulldog;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Bulldog extends RealmObject {
    @PrimaryKey
    private String id;
    private String name, age;
    private byte[] image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
