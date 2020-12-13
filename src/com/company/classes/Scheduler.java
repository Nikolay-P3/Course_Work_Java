package com.company.classes;

import com.company.Main;
import com.company.interfaces.ITime;

import java.util.ArrayList;

public class Scheduler implements ITime {
    static ArrayList<Process> doneProcesses;
    static Queue queue;

    CPU cpu;
    MemScheduler memScheduler;
    ClockGenerator clockGenerator;

    public Scheduler() {
        queue = new Queue();

        doneProcesses = new ArrayList<>();

        this.cpu = new CPU(Configuration.coreCount);
        this.memScheduler = new MemScheduler();
        this.clockGenerator = new ClockGenerator();

        this.clockGenerator.addListener(cpu);
        this.clockGenerator.addListener(this);
    }

    public void Start()
    {
        preLaunchInit();
        this.clockGenerator.run();
    }

    private void preLaunchInit()
    {
        MemScheduler.add(new MemoryBlock(0,100,null));

        queue.Add(Configuration.initPCount);
    }

    private void addJob()
    {
        if(Utils.getRandBool()) {
            queue.Add(Utils.getRandInt(Configuration.minValue));
        }
        updateTable();
    }


    @Override
    public String toString() {
        return "Scheduler{\n"+cpu+'\n'+memScheduler+'\n'+queue+"\nDone:"+doneProcesses+"\n}";
    }

    public static void PDone(Process process)
    {
        if(Utils.getRandBool()) {
            process.setStatus(Status.Finished);
            doneProcesses.add(process);
        }
        else
        {
            process.setStatus(Status.Waiting);
            queue.addProcess(process);
        }
    }

    private void clearOutdated()
    {
        if(ClockGenerator.getTick()% Configuration.rmOldPIterator ==0) {
            queue.cancelOutdated();
        }
    }

    private void setJobToCPU()
    {
        for (int i = 0; i< Configuration.coreCount; i++) {
            int _tmpInt = cpu.getFreeCore();
            if (_tmpInt >= 0) {
                cpu.setCoreJob(_tmpInt, queue.getNextProcess());
            }
        }
    }

    public void updateTable()
    {
        Main.controller.updateTable(queue,doneProcesses);}

    @Override
    public void timerStep() {
        clearOutdated();
        setJobToCPU();
        addJob();
    }
}
