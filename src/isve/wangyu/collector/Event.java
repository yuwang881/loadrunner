/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isve.wangyu.collector;

/**
 *
 * @author yuwang881@gmail.com
 */
public class Event {
    public String eventID;
    public String eventMessage;
    public long timpstamp;
    public long duration;
    
    public Event(String id,String message,long time,long dur){
        eventID = id;
        eventMessage = message;
        timpstamp = time;
        duration = dur;
    }
    
    
}
