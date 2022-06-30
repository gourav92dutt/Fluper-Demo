package com.riziliant.myapplication;

import java.io.Serializable;

public class StoresDictionary   {
    public StoresDictionary(String rating, String like) {
        this.rating = rating;
        this.like = like;
    }

    private String rating, like;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }
}
