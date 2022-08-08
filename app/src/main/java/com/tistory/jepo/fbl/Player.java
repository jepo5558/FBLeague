package com.tistory.jepo.fbl;

import android.graphics.drawable.Drawable;

public class Player {
    private String name;

    public Drawable getPhoto() {
        return photo;
    }

    public void setPhoto(Drawable photo) {
        this.photo = photo;
    }

    private Drawable photo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
