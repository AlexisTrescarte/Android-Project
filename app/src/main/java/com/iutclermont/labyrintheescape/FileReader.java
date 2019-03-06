package com.iutclermont.labyrintheescape;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

public class FileReader {

    private String chemin;

    public FileReader(int niv){
        switch (niv){
            case 1 : chemin = "niv1";break;
        }
    }

    public List<Wall> getWall(int width, Context context) throws IOException {

        List<Wall> result = new LinkedList<>();
        int size=width/16;
        int content;
        int x=0;
        int y=0;
        int i = 0;


        InputStream myFich = context.getResources().openRawResource(R.raw.niv1);


        while ((content = myFich.read()) != -1) {
            switch ((char)content){
                        case '1' :
                            Wall wall = new Wall(x,y,'W');
                            result.add(wall);break;
                        case 'A' :
                            Wall arrive = new Wall(x,y,'A');
                            result.add(arrive);break;
                        case 'B':
                            Wall bush = new Wall(x,y,'B');
                            result.add(bush);break;
            }
            if(i>16){
                i=0;
                x=0;
                y=y+size;
            }
            else{
                i++;
                x=x+size;
            }
        }
        return result;
    }
}
