package com.fangjian.framework.core.jdbc.impl.mysql;

import java.sql.DriverManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fangjian.framework.core.commom.PropertyObj;
import com.fangjian.framework.core.jdbc.JdbcDao;
import com.mysql.jdbc.Connection;
/**
 * 
 * 获取mysql的connnect
 * @author fangjian
 *
 */
public class MySqlJdbcDaoImpl implements JdbcDao {
	protected Log log = LogFactory.getLog(MySqlJdbcDaoImpl.class);
	
	public Connection getConnection() {
		
		Connection con = null;  //创建用于连接数据库的Connection对象  
        try {  
            Class.forName(PropertyObj.getInstance().getPropertiesByKey("/config.properties","driverClassName"));// 加载Mysql数据驱动  
            String url=PropertyObj.getInstance().getPropertiesByKey("/config.properties","jdbc_url");
            String username =PropertyObj.getInstance().getPropertiesByKey("/config.properties","jdbc_username");
            String password =PropertyObj.getInstance().getPropertiesByKey("/config.properties","jdbc_password");
            con = (Connection) DriverManager.getConnection(url, username, password);// 创建数据连接
            if(con==null){
            	System.out.println("数据库链接失败");
            }
        } catch (Exception e) {  
        	log.info("mysql数据库连接失败" + e.getMessage());  
        }  
        return con; //返回所建立的数据库连接  
	}

}
