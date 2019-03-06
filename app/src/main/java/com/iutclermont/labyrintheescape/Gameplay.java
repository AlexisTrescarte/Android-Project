package com.iutclermont.labyrintheescape;

import android.content.Context;
import android.graphics.Color;
import android.graphics.RectF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;

import java.io.IOException;
import java.util.List;

import static java.lang.Math.abs;

public class Gameplay extends AppCompatActivity {

    private GameThread myThread;
    private Personage personnage;
    private myCanvas myCanvas;
    private SensorManager mSensorManager = null;
    private Sensor mAccelerometer = null;
    private List<Wall> wallList;
    FileReader reader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //avoir la taille de l'écran
        Display ecran = getWindowManager().getDefaultDisplay();
        final int Width= ecran.getWidth();
        final int Height=ecran.getHeight();
        //

        personnage=new Personage(Width/16);

        //On récupére les item du niveau
        reader=new FileReader(1);
        try {
            wallList = reader.getWall(Width,this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //met en place la surface
        myCanvas = new myCanvas(this);
        myCanvas.setItemImg(this,Width,wallList);
        myCanvas.setBackgroundColor(Color.GRAY);
        setContentView(myCanvas);
        myCanvas.setPers(personnage);

        //taille des objet
        final int size = Width/16;

        //on gére l'accelerométre
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        SensorEventListener mSensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                // La valeur sur l'axe x
                float y = event.values[0];
                // La valeur sur l'axe y
                float x = event.values[1];

                Boolean collision=true;
                float potentialX=0;
                float potentialY=0;

                System.out.println(abs(x)+"   "+abs(y));

                if(abs(x)>4 || abs(y)>4){
                    if(abs(x)>abs(y)){
                        if(x>0){
                            potentialX= personnage.getX()+size;
                        }
                        else{
                            potentialX= personnage.getX()-size;
                        }
                         potentialY=personnage.getY();
                    }
                    else{
                        potentialX=personnage.getX();
                        if(y>0){
                            potentialY= personnage.getY()+size;
                        }
                        else{
                            potentialY= personnage.getY()-size;
                        }
                    }

                    // Puis on modifie les coordonnées en fonction de la vitesse et actualisation de la hitbox
                    personnage.setVisible(true);
                    collision=false;

                    //vérification de la position dans l'espace de jeux
                    if(potentialX <0){
                        potentialX=0;
                    }
                    if(potentialX>Width-size){
                        potentialX=Width-size;
                    }
                    if(potentialY<0){
                        potentialY=0;
                    }
                    if(potentialY>Height-size){
                        potentialY=Height-size;
                    }
                    //collision avec les murs
                    for (Wall wall:wallList) {

                        if(potentialX==wall.getX() && potentialY==wall.getY()){
                            switch (wall.getType()){
                                case 'W' :
                                    collision=true;
                                    break;
                                case 'B':
                                    personnage.setVisible(false);
                                    break;
                                case 'A':
                                    //le jouer à gagner
                                    break;
                            }
                        }
                    }
                }





                if(collision==false){
                    personnage.setX(potentialX);
                    personnage.setY(potentialY);
                }
                setContentView(myCanvas);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }

        };
        mSensorManager.registerListener(mSensorEventListener, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

    }



}
