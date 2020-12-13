package com.company.classes;

import com.company.interfaces.ITime;

public class CPU implements ITime
{
    Core[] cores;

    public CPU(final int number)
    {
        this.cores = new Core[number];

        for(int i = 0; i<number; i++)
            cores[i]=new Core();
    }
    public Core[] getCores(){return cores;}

    @Override
    public String toString() {
        String _tmp=" ";
        for (int i =0; i<cores.length; i++)
            _tmp+="\t{Core#"+i+"\t"+cores[i].getState()+"}";
        return "CPU{" +_tmp+"}";
    }

    public void setCoreJob(int coreNumb, Process process)
    {
        if(process!=null) {
            cores[coreNumb].currentProcess = process;
            cores[coreNumb].isFree = false;
        }
    }

public int getFreeCore()
{
    for (int i =0; i<cores.length; i++)
        if(cores[i].isFree)
            return i;
    return -1;
}






    @Override
    public void timerStep() {
        for(Core core : cores)
        {
            if(!core.isFree) {
                Process _tmProcess = core.getCurrentProcess();

                _tmProcess.setBursTime(_tmProcess.getBursTime() + 1);
                if (_tmProcess.getBursTime() == _tmProcess.getTickWorks()) {
                    Scheduler.PDone(core.currentProcess);
                    core.currentProcess = null;
                    core.isFree = true;
                }
            }
        }
    }
}
