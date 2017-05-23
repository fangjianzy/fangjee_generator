package com.fangjian.framework.common.util;

import java.io.IOException;
import java.util.Properties;


/**   
 * 类名称：Config   </br>
 * 类描述：  读取application.properties配置文件 </br>
 * 创建人：fangjian </br>
 * 创建时间：2016年1月18日 上午11:20:47   </br>
 * @version  0.1  </br>
 */
public class Config {

	 /**配置类实例*/
    private static Config config = null;

    private Properties props = new Properties();

    /**
     * 获取配置类的一个实例
     * @return Config
     */
    public static Config getInstance() {
        if (config == null) {
            config = new Config();
            config.init();
        }
        return config;
    }

    private void init() {
        try {
            props.load(this.getClass().getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 根据键获取值
     * @param key String
     * @return String
     */
    public String getValue(String key) {
        return props.getProperty(key);
    }

    /**
     * 设置键-值
     * @param key String
     * @param value String
     */
    public void setValue(String key, String value) {
        props.setProperty(key, value);
    }

}

