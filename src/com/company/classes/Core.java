package com.company.classes;

public class Core {
   public boolean isFree=true;
   public Process currentProcess = null;

   public String getState() {
      String _tmp = " ";
      if(this.isFree)
         _tmp+="is free";
      else
         _tmp+= "Working on Process: "+currentProcess;


      return _tmp ;
   }

   public Process getCurrentProcess(){return currentProcess;}
}
