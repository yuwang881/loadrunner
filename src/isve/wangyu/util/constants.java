/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isve.wangyu.util;

/**
 *
 * @author yuwang881@gmail.com
 */
public class constants {
    public static int vuser_init = ConfigUtil.getInt("vuser_init");
    public static int vuser_max = ConfigUtil.getInt("vuser_max");
    public static int  interval= ConfigUtil.getInt("interval");
    public static int  duration= ConfigUtil.getInt("duration");
    public static int  repeat= ConfigUtil.getInt("repeat");
    public static int  thinkingtime= ConfigUtil.getInt("thinkingtime");
    public static String test_mode  = ConfigUtil.get("test_mode");
    public static String  action_class = ConfigUtil.get("action_class");
    public static String  collector_class = ConfigUtil.get("collector_class");
    public static String  action_config = ConfigUtil.get("action_config");

}
