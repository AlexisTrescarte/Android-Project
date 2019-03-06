package com.iutclermont.labyrintheescape;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.List;

public class myCanvas extends View {

    private Bitmap myPersImg;
    private Bitmap myWallImg;
    private Bitmap myBushImg;
    private Bitmap myArriveImg;
    private Paint myPaint;
    private Personage myPers;
    private List<Wall> listWall;

    public myCanvas(Context context) {
        super(context);
        myPaint = new Paint();
        myPaint.setColor(Color.BLACK);
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawBitmap(myPersImg,myPers.getX(),myPers.getY(),myPaint);

        for (Wall wall:listWall) {
            switch (wall.getType()){
                case 'A' :
                    canvas.drawBitmap(myArriveImg,wall.getX(),wall.getY(),myPaint);break;
                case 'B' :
                    canvas.drawBitmap(myBushImg,wall.getX(),wall.getY(),myPaint);break;
                case 'W' :
                    canvas.drawBitmap(myWallImg,wall.getX(),wall.getY(),myPaint);break;

            }
        }
    }

    public void setItemImg(Context c , int Width, List<Wall> listWall){
        int size=Width/16;
        //pour le personnage
        Resources res = c.getResources();
        Bitmap bitmapP = BitmapFactory.decodeResource(res,R.mipmap.perso);
        this.myPersImg=Bitmap.createScaledBitmap(bitmapP , size, size, false);

        //pour les murs
        Bitmap bitmapW = BitmapFactory.decodeResource(res,R.mipmap.wall);
        this.myWallImg=Bitmap.createScaledBitmap(bitmapW , size, size, false);

        //pour l'arrivé
        Bitmap bitmapA = BitmapFactory.decodeResource(res,R.mipmap.arrive);
        this.myArriveImg=Bitmap.createScaledBitmap(bitmapA , size, size, false);

        //pour les buisson
        Bitmap bitmapB = BitmapFactory.decodeResource(res,R.mipmap.bush);
        this.myBushImg=Bitmap.createScaledBitmap(bitmapB , size, size, false);

        this.listWall=listWall;
    }




    public void setPers(Personage myPers){
        this.myPers=myPers;
    }

}
