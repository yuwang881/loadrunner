/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isve.wangyu.action;

/**
 *
 * @author yuwang881@gmail.com
 */
public class MockAction implements UserAction{

    @Override
    public void execute() {
       try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
    }

    @Override
    public void close() {
        
    }
    
}
