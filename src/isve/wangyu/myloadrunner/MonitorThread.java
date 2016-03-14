/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isve.wangyu.myloadrunner;

import java.io.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author yw137672
 */
public class MonitorThread implements Runnable{
    
    private ConcurrentLinkedQueue<VUser>  vusers = new ConcurrentLinkedQueue<VUser>() ;


    public void registThread (VUser vuser) {
        vusers.offer(vuser);
    }

    public void run() {
        System.out.println("Print Any key to Stop the Computing....");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        try {
            in.readLine();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        
        VUser current_user;;
        while (vusers.peek() != null) {
            current_user =vusers.poll();
            current_user.stop();
        }
      
       
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        
         System.out.println("Good Bye");
        System.exit(0);
    }
    
}