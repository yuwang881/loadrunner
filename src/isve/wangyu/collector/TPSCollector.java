/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isve.wangyu.collector;

import java.util.concurrent.atomic.AtomicLong;


/**
 *
 * @author yuwang881@gmail.com
 */
public class TPSCollector implements DataCollector{
    public static AtomicLong accum= new AtomicLong(0); 
    
    private long tps=0;
    private long timer=0;

    @Override
    public void publish(Event event) {
        tps ++;
        if (event.timpstamp - timer > 1000) {
            timer = event.timpstamp;
            flushout();
            tps =0;
        }
    }
    
    @Override
    public void start() {
        timer = System.currentTimeMillis();
    }
    
    private void flushout() {
        accum .set(tps+accum.get());
    }
    
}
