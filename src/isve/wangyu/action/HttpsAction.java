/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isve.wangyu.action;

import isve.wangyu.util.HttpsURLHelper;

/**
 *
 * @author yuwang881@gmail.com
 */
public class HttpsAction  extends HttpAction{
    
    public HttpsAction() throws Exception{
        super();
        setURLHelper(new HttpsURLHelper(true));
    }
    
}
