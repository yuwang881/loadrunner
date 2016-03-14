/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isve.wangyu.myloadrunner;

import isve.wangyu.action.UserAction;
import isve.wangyu.collector.DataCollector;
import isve.wangyu.util.constants;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 *
 * @author yuwang881@gmail.com
 */
public class MyLoadRunner {
    
    private final static int MAX_THREADS = constants.vuser_max;
    private final static Executor pool = Executors.newFixedThreadPool(MAX_THREADS+3);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        
        //read config to get the instance of  Action Class
        System.out.println(constants.collector_class);
        Class actionClass  = Class.forName(constants.action_class);
        Class collectorClass  = Class.forName(constants.collector_class);
         
  
        //start monitor thread
        MonitorThread  monitor= new MonitorThread();
        pool.execute(monitor);
        
        MetricsReporter myReporter = new MetricsReporter(null,null);
        monitor.registThread(myReporter);
        pool.execute(myReporter);
          
        //start and register VUser threads
        for (int i=0;i<constants.vuser_init;i++){
            UserAction myAction =  (UserAction)actionClass.newInstance();
            DataCollector myCollector = (DataCollector)collectorClass.newInstance();
            VUser vu = new VUser(myAction,myCollector);
            vu.setUid("vuser:"+i);
            monitor.registThread(vu);
            pool.execute(vu);
        }
        
        for (int i = constants.vuser_init-1; i < constants.vuser_max; i = i + constants.interval) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            for (int j = 0; j < constants.interval; j++) {
                UserAction myAction =  (UserAction)actionClass.newInstance();
                DataCollector myCollector = (DataCollector)collectorClass.newInstance();
                VUser vu = new VUser(myAction,myCollector);
                vu.setUid("vuser:"+(i+j));
                monitor.registThread(vu);
                pool.execute(vu);
            }
        }

     

        
        
        
        
    }
    
}
