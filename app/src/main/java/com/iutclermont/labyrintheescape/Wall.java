package com.iutclermont.labyrintheescape;

import android.graphics.RectF;

public class Wall {

    private int X;
    private int Y;
    private char type;


    public Wall(int x , int y,char type){
        this.X=x;
        this.Y=y;
        this.type=type;

    }

    public int getX() {
        return X;
    }


    public int getY() {
        return Y;
    }

    public char getType() {
        return type;
    }

}
