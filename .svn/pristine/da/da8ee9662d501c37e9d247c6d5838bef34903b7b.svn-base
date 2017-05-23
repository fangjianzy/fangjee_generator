package com.fangjian.framework.common.util;

import java.sql.DriverManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.fangjian.framework.entity.sys.Sysdatesource;
import com.mysql.jdbc.Connection;

/**
 * 代码生成器-检测数据库连接 </br>
 * @version 1.0.0
 * @author 方坚
 *
 */
public class CheckConnection {
	protected Log log = LogFactory.getLog(CheckConnection.class);
	public boolean getConnection(Sysdatesource dto) {
		boolean b = false;
		Connection con = null;  //创建用于连接数据库的Connection对象  
        try {  
            Class.forName(dto.getDriviceClassName());// 加载Mysql数据驱动  
            String url=dto.getUrl();
            String username =dto.getUsername();
            String password =dto.getPassword();
            con = (Connection) DriverManager.getConnection(url, username, password);// 创建数据连接  
        } catch (Exception e) {
        	b= false;
        	log.error("mysql数据库连接失败" + e.getMessage());  
        }
        if(con==null){
        	b= false;
        }else{
        	b = true;
        }
        return b; 
	}
}
