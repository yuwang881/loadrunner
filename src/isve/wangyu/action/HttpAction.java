/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isve.wangyu.action;

import isve.wangyu.util.HttpsURLHelper;
import isve.wangyu.util.StringBM;
import isve.wangyu.util.constants;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;




/**
 *
 * @author yuwang881@gmail.com
 */
public class HttpAction implements UserAction{
    List<HttpTest> httptests = new ArrayList<HttpTest>();
    HttpsURLHelper myHelper = null;
    
    public void setURLHelper(HttpsURLHelper h){
        myHelper= h;
    }
    
    public HttpAction() throws Exception {
        //set helper
        setURLHelper(new HttpsURLHelper());
        
        // config from config file
        Path config_file = Paths.get(constants.action_config);
        boolean isReadable= Files.isReadable(config_file);
        if (!isReadable) {
            throw new FileNotFoundException();
        }
        
        StringBM bm1 = new StringBM("<action>");
        StringBM bm2 = new StringBM("testid>");
        StringBM bm3 = new StringBM("method>");
        StringBM bm4 = new StringBM("testurl>");
        StringBM bm5 = new StringBM("payload>");
        
        bm1.init();
        bm2.init();
        bm3.init();
        bm4.init();
        bm5.init();
       
        
        byte[] fileArray = Files.readAllBytes(config_file);
       
        int index =0;
        int tmp=0;
        String value;
        
        index = bm1.indexOf(fileArray, index);
        
        while (index > 0){
            HttpTest ht = new HttpTest();
            
            index = bm2.indexOf(fileArray, index);
            tmp =bm2.indexOf(fileArray, index+4);
            value = new String(fileArray,index+7,tmp-index-9);
            ht.testId = value.trim();
            
            index = bm3.indexOf(fileArray, index);
            tmp =bm3.indexOf(fileArray, index+4);
            value = new String(fileArray,index+7,tmp-index-9);
            ht.method= value.trim();
            
            index = bm4.indexOf(fileArray, index);
            tmp =bm4.indexOf(fileArray, index+4);
            value = new String(fileArray,index+8,tmp-index-10);
            ht.url= value.trim();
            
            index = bm5.indexOf(fileArray, index);
            tmp =bm5.indexOf(fileArray, index+4);
            value = new String(fileArray,index+8,tmp-index-10);
            ht.payload= value.trim();
            
            httptests.add(ht);
            
            index = bm1.indexOf(fileArray, index);
        }
        
        
    }
    
    @Override
    public void execute() {
        for (HttpTest ht : httptests) {
            if (ht.method.equals("get")) {
                try {
                    String result = myHelper.get(ht.url);
                    //System.out.println(result);
                } catch (IOException ex) {
                    System.out.println("Http Get exception!");
                    ex.printStackTrace();
                }
            } else if (ht.method.equals("post")) {
                try {
                    String result = myHelper.Post(ht.url, ht.payload);
                    //System.out.println(result);
                } catch (IOException ex) {
                    System.out.println("Http POST exception!");
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public void close() {
        try {
            myHelper.close();
        } catch (IOException ex) {
            
        }
    }
    
    
    class HttpTest {
        public String testId;
        public String method;
        public String url;
        public String payload;
    }
    
}
