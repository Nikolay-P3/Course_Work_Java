package com.company.classes;

import com.company.interfaces.ITime;

import java.util.ArrayList;
import java.util.TimerTask;

public class ClockGenerator extends TimerTask {
    ArrayList<ITime> listenersList = new ArrayList<>();

    private static int tick;
    public static int getTick() {
        return tick;
    }
    public static void TickUP(){
        tick++;
    }
    public static void clearTime(){tick =0;}

    @Override
    public void run() {
        while (true)
        {
            try {
                Thread.sleep(1000,Configuration.tickIncrement);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            TickUP();

            for(ITime listener : listenersList)
                listener.timerStep();

        }
    }

    public void addListener(ITime listener)
    {
        listenersList.add(listener);
    }
}
