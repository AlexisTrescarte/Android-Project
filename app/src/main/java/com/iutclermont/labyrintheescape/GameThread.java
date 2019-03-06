package com.iutclermont.labyrintheescape;

public class GameThread extends Thread {

    private Boolean running = false;

    @Override
    public void run() {
        while(running){

        }
    }

    public void setRunning(Boolean b ){
        this.running=b;
    }


}
