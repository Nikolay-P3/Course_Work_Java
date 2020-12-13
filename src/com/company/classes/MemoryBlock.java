package com.company.classes;

import java.util.Comparator;

public class MemoryBlock {
    int start;
    int end;
    Process process;

    public static Comparator<MemoryBlock> byEnd = Comparator.comparingInt(o -> o.end);

    public MemoryBlock(int start, int end, Process process) {
        this.start=start;
        this.end=end;
        this.process=process;
    }

    @Override
    public String toString() {
        return "MemoryBlock{" +"start=" + start +"\tend=" + end +"P"+process+'}';
    }
}
