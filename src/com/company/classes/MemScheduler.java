package com.company.classes;


import java.util.ArrayList;

public class MemScheduler {
private static ArrayList<MemoryBlock> memoryBlocks = new ArrayList<>();

public static int SearchMB(int size)
{
    memoryBlocks.sort(MemoryBlock.byEnd);

    for(int i=0;i<memoryBlocks.size()-1;i++) {
        if (memoryBlocks.get(i + 1).start - memoryBlocks.get(i).end >= size+1)
            return memoryBlocks.get(i).end + 1;
    }
    if(Configuration.memory - memoryBlocks.get(memoryBlocks.size()-1).end>=size+1)
        return memoryBlocks.get(memoryBlocks.size()-1).end+1;
    else
        return Configuration.memory;
}

public static boolean fillMB(Process process)
{
    int _start = SearchMB(process.getMemory());

    if (_start!= Configuration.memory)
    {
        memoryBlocks.add(new MemoryBlock(_start, _start+process.getMemory(),process));
        return true;
    }
    else
    return false;
}

public static void releaseMB(Process process)
{
    memoryBlocks.removeIf(mb -> mb.process == process);
}

public static void clearMem(){
memoryBlocks.clear();
}

public static void add(MemoryBlock memoryBlock) {
    memoryBlocks.add(memoryBlock);
}

    @Override
    public String toString() {
        return "MemScheduler{"+memoryBlocks+"}";
    }
}
