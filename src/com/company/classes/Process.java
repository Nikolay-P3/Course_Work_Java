package com.company.classes;

import java.util.Comparator;

public class Process {
    private int id;        //after create
    private String name;    //rand
    private int priority;  //rand + on work
    private Status status;    //rand + on work
    private int tickWorks;       //rand
    private int memory;     //rand
    private final int timeIn;     //after create
    private int bursTime;   //on work

    public Process(int id) {
        this.id = id;
        this.name = Utils.getRandString(Utils.getRandInt(Configuration.minProcName,Configuration.maxProcName));
        this.memory = Utils.getRandInt(Configuration.minMemsize, Configuration.maxMemsize);
        this.priority= Utils.getRandInt(Configuration.maxPriority);
        this.tickWorks = Utils.getRandInt(Configuration.minTickWork, Configuration.maxTickWork);
        this.timeIn = ClockGenerator.getTick();
        this.bursTime=0;
        this.status = Status.Ready;
    }
    //________Setters________\\

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setBursTime(int bursTime) {
        this.bursTime = bursTime;
    }

    //________Getters________\\


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public int getTickWorks() {
        return tickWorks;
    }

    public int getMemory() {
        return memory;
    }

    public int getTimeIn() {
        return timeIn;
    }

    public int getBursTime() {
        return bursTime;
    }

    public Status getStatus(){return status;}


    public static Comparator<Process> byPriority = Comparator.comparingInt(o -> o.priority);

    //________toString________\\

    @Override
    public String toString() {
        return "Process" +
                "\tid=" + id +
                "\tname=" + name +
                "\tpriority=" + priority +
                "\tstate=" + status +
                "\ttick=" + tickWorks +
                "\tmemory=" + memory +
                "\ttimeIn=" + timeIn +
                "\tbursTime=" + bursTime+'\n';
    }

}
