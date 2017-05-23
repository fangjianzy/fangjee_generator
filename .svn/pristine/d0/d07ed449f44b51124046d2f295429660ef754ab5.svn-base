package com.fangjian.framework.core.commom;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 负责读取config.properties配置文件
 * @author 方坚
 *
 */
public class PropertyObj {
	
	protected Log log = LogFactory.getLog(PropertyObj.class);
	
	private static PropertyObj instance=new PropertyObj();
	
	private PropertyObj(){}
	
	public static PropertyObj getInstance(){
		
		return instance;
	}
	
	/**
	 * 取某个路径下properties的key对应的值 
	 * @param path 路径
	 * @param key  key=value
	 * @return 值value
	 */
	public String getPropertiesByKey(String path,String key){
		
		Properties prop = new Properties();   
		
        InputStream in = Object.class.getResourceAsStream(path); 
        try {   
            prop.load(in);   
        } catch (IOException e) { 
        	log.info("项目跟目录下没有找到config.properties配置文件!");
            e.printStackTrace();
        }
        return  prop.getProperty(key).trim();   
	}
	
	public static void main(String[] args) {
		System.out.println(PropertyObj.getInstance().getPropertiesByKey("/config.properties","jdbc_url"));
	}
}
