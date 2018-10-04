package com.example.jdr006.beautifulbulldog;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Vote extends RealmObject {
    private Bulldog bulldog;
    private User Owner;
    private int rating;

    public Bulldog getBulldog() {
        return bulldog;
    }

    public void setBulldog(Bulldog bulldog) {
        this.bulldog = bulldog;
    }

    public User getOwner() {
        return Owner;
    }

    public void setOwner(User owner) {
        Owner = owner;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
