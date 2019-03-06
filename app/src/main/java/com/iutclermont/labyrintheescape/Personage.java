package com.iutclermont.labyrintheescape;

import android.graphics.RectF;

public class Personage {

    private float X;
    private float Y;

    private Boolean visible;
    private int size;


    public Personage(int size){
        this.visible=true;
        this.X=0;
        this.Y=0;
        this.size=size;
    }


    public float getX() {
        return X;
    }

    public void setX(float x) {
        X = x;
    }

    public float getY() {
        return Y;
    }

    public void setY(float y) {
        Y = y;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
}
