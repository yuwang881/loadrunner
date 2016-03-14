/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isve.wangyu.myloadrunner;

import isve.wangyu.action.UserAction;
import isve.wangyu.collector.DataCollector;
import isve.wangyu.collector.Event;
import isve.wangyu.util.constants;

/**
 *
 * @author yuwang881@gmail.com
 */
public class VUser implements Runnable {
    private String uid;
    private UserAction myAction;
    private DataCollector myCollector;
    private volatile  boolean stop = false;
    private int looptimes = 0;
    private long duration = 0;
    private long basetime = 0;
    
    public VUser(UserAction action,DataCollector collector){
        myAction = action;
        myCollector = collector;
        basetime = System.currentTimeMillis();
    }
    
    public void setUid(String id){
        uid = id;
    }
    
    @Override
    public void run() {
        //start timer
        myCollector.start();
        
        while(!isStopped()){
            
            myAction.execute();
            looptimes ++;
            long currenttime =System.currentTimeMillis();
            duration = currenttime - basetime;
            
            //data collector here
            Event event = new Event(uid+looptimes,"",currenttime,duration);
            myCollector.publish(event);
            
            if (shouldFinish()) break;
      
            if (constants.thinkingtime > 0) {
                try {
                    Thread.sleep(constants.thinkingtime);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
        System.out.println("Thread is stopped!");
        myAction.close();
    }
    
    private boolean shouldFinish(){
        if(constants.test_mode.equals("duration")) return (duration/1000 > constants.duration);
        return looptimes > constants.repeat;
    }
    
    public UserAction getAction() {
        return myAction;
    }
    

    public void stop() {
        stop = true;
    }
    
      public boolean isStopped() {
        return stop;
    }

}
