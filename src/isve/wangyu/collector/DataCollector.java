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
public interface DataCollector {
    public void publish(Event event);
    public void start();
}
