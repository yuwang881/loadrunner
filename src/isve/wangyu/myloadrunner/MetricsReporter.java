/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isve.wangyu.myloadrunner;

import isve.wangyu.action.UserAction;
import isve.wangyu.collector.DataCollector;
import isve.wangyu.collector.Event;
import isve.wangyu.collector.TPSCollector;
import isve.wangyu.util.constants;



/**
 *
 * @author yuwang881@gmail.com
 */
public class MetricsReporter extends VUser {
  

    private volatile  boolean stop = false;
    private long lastrecord =0;

    public MetricsReporter(UserAction action, DataCollector collector) {
        super(action, collector);
    }

  
    
    @Override
    public void run() {

        while (!isStopped()) {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            
            long current = TPSCollector.accum.get();
            int tps  = (int) ((current-lastrecord) /2);
            lastrecord = current;
            System.out.println("TPS: "+ tps);
            
        }
        System.out.println("Thread is stopped!");

    }



    public void stop() {
        stop = true;
    }
    
      public boolean isStopped() {
        return stop;
    }

}
