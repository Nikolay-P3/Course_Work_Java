package com.company.classes;

import java.util.ArrayList;

public class Queue {
    private final ArrayList<Process> queue;
    private final ArrayList<Process> rejectedQueue;
    private int PID;

    public Queue()
    {
        this.queue = new ArrayList<>();
        this.rejectedQueue = new ArrayList<>();
        this.PID=1;
    }

    public void Add(final int PCount)
    {
        for (int i = 0; i < PCount; i++) {
            Process p = new Process(this.PID);
            this.PID++;
            addProcess(p);
        }
    }

    public void addProcess(Process p)
    {
        if (p.getStatus() == Status.Waiting)
            p.setBursTime(p.getBursTime()+(int)(p.getBursTime()/Configuration.ZombieDiv));

        if(MemScheduler.fillMB(p)) {
            this.queue.add(p);
        }
        else {
            if (p.getStatus() == Status.Waiting)
                p.setStatus(Status.Canceled);
            else
                p.setStatus(Status.Rejected);

            rejectedQueue.add(p);
        }
    }

    public void Remove(Process process)
    {
        queue.remove(process);
        MemScheduler.releaseMB(process);
    }

    public void cancelOutdated()
    {
            for (int i=queue.size()-1; i>=0;i--)
                if (ClockGenerator.getTick() >= queue.get(i).getTimeIn() * Configuration.PRmMultiplier)
                    cancelProcess(queue.get(i));
    }

    private void cancelProcess(Process process)
    {
        Remove(process);
        process.setStatus(Status.Canceled);
        rejectedQueue.add(process);
    }

    public ArrayList<Process> getQueue() {
        return queue;
    }

    public ArrayList<Process> getRejectedQueue() {
        return rejectedQueue;
    }

    public Process getNextProcess() {
        queue.sort(Process.byPriority);
        if(queue.size()!=0) {
            Process _tmp = queue.get(0);
            this.Remove(_tmp);
            _tmp.setStatus(Status.Running);
            return _tmp;
        }
        return null;
    }
}
