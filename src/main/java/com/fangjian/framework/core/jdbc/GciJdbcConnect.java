package com.fangjian.framework.core.jdbc;

import java.sql.Connection;

import com.fangjian.framework.core.commom.PropertyObj;
import com.fangjian.framework.core.jdbc.impl.mysql.MySqlJdbcDaoImpl;
import com.fangjian.framework.core.jdbc.impl.oracle.OracleJdbcDaoImpl;


/**
 * 工厂模式得到连接
 * @author fangjian
 *
 */
public class GciJdbcConnect {
	 /* 获取数据库连接的函数*/  
	static String dbtype = PropertyObj.getInstance().getPropertiesByKey("/config.properties", "dbType");
	    
    public static Connection getConnection() {  
    	Connection conn = null;
    	if("mysql".equals(dbtype)){
    		conn= new MySqlJdbcDaoImpl().getConnection();
    	}else if("oracle".equals(dbtype)){
    		conn= new OracleJdbcDaoImpl().getConnection();
    	}
    	return conn;
    }
}
