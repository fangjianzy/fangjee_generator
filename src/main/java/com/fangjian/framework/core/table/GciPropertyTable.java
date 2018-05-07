package com.fangjian.framework.core.table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fangjian.framework.common.util.UnderlineToCodeTools;
import com.fangjian.framework.core.commom.GciCodeTypeMap;
import com.fangjian.framework.core.jdbc.GciJdbcConnect;
/**
 * 针对Mysql根据表获取所有字段列表
 * @author fangjian
 *
 */
public class GciPropertyTable {
	// 创建静态全局变量  
    static Connection conn;  
    static PreparedStatement ps;
    static ResultSet ret;
    protected Log log = LogFactory.getLog(GciPropertyTable.class);
    /**
     * jdbc方式根据表名和表所在库名得到该表所有字段信息,返回字段列表信息
     * @param sechma 库名
     * @param tabelname 表名
     * @return 表所有字段的详细信息
     * @throws SQLException
     */
    public static List<GciTable> getTableInfoByName(String sechma,String tabelname) throws SQLException{
    	List<GciTable> ts = new ArrayList<GciTable>();
    	conn = GciJdbcConnect.getConnection();
    	String SQL="SELECT t.TABLE_SCHEMA,t.TABLE_NAME,t.COLUMN_NAME,t.ORDINAL_POSITION,t.DATA_TYPE,t.CHARACTER_MAXIMUM_LENGTH,t.COLUMN_KEY,t.COLUMN_COMMENT,UCASE(LEFT(t.COLUMN_NAME,1)) AS upCo,SUBSTRING(t.COLUMN_NAME,2) AS lwCo FROM information_schema.COLUMNS t WHERE t.TABLE_NAME= ? AND  table_schema = ? ORDER BY t.ORDINAL_POSITION ASC ";
    	ps = conn.prepareStatement(SQL);
    	ps.setString(1, tabelname);
    	ps.setString(2, sechma);
    	ret = ps.executeQuery();
    	GciTable table = null;
    	while(ret.next()){
    		table = new GciTable();
    		table.setTable_schema(ret.getString("TABLE_SCHEMA"));
    		table.setTable_name(ret.getString("TABLE_NAME").toLowerCase());
    		table.setClumn_name(ret.getString("COLUMN_NAME").toLowerCase());
    		table.setOrdinal_position(ret.getString("ORDINAL_POSITION"));//字段排序
    		table.setData_type(ret.getString("DATA_TYPE"));
    		table.setLength(Integer.parseInt(ret.getString("CHARACTER_MAXIMUM_LENGTH")==null?"11":ret.getString("CHARACTER_MAXIMUM_LENGTH")));//int类型没有默认长度，这里给默认11位
    		table.setColumn_key(ret.getString("COLUMN_KEY"));
    		table.setColumn_comment(ret.getString("COLUMN_COMMENT")==null?ret.getString("COLUMN_NAME"):ret.getString("COLUMN_COMMENT"));
    		table.setColumn_getset(ret.getString("upCo")+ret.getString("lwCo").toLowerCase());
    		table.setColumn_javatype(GciCodeTypeMap.getJavaTypeBySqlType().get(ret.getString("DATA_TYPE")));
    		table.setColumn_javaMybatintype(GciCodeTypeMap.getMySqlTypeBySqlType().get(ret.getString("DATA_TYPE")));
    		//生成驼峰代码
    		table.setJavaColumnFileNameCode(UnderlineToCodeTools.underlineToCamel2(ret.getString("COLUMN_NAME").toLowerCase()));
    		System.out.println(table.getJavaColumnFileNameCode());
    		table.setJavaColumnGetSetFileNameCode(UnderlineToCodeTools.upperCase(UnderlineToCodeTools.underlineToCamel2(ret.getString("COLUMN_NAME"))));
    		
    		
    		//自动生成验证信息相关字段熟悉
    		table.setDataType(GciCodeTypeMap.getValidateDateType().get(ret.getString("DATA_TYPE")));
    		if("varchar".equals(ret.getString("DATA_TYPE"))){
    			table.setRequire("true");
    			table.setRequireClass("validate[required,length[0,"+table.getLength()/2+"]]");
    			table.setMsg(table.getColumn_comment()+"为必填项,且最多"+table.getLength()/2+"个汉字");
    		}else if("int".equals(ret.getString("DATA_TYPE"))){
    			table.setRequire("true");
    			table.setRequireClass("validate[required,custom[onlyNumber],length[0,8]]");
    			table.setMsg(table.getColumn_comment()+"只能输入整数");
    		}else if("date".equals(ret.getString("DATA_TYPE"))){
    			table.setRequire("true");
    			table.setRequireClass("date validate[required,custom[date]]  dateFmt='yyyy-MM-dd'");
    			table.setMsg(table.getColumn_comment()+"只允许输入日期");
    		}else if("timestamp".equals(ret.getString("DATA_TYPE"))){
    			table.setRequire("true");
    			table.setRequireClass("date validate[required,custom[date]] dateFmt='yyyy-MM-dd HH:mm:ss'");
    			table.setMsg(table.getColumn_comment()+"只允许输入日期");
    		}else if("double".equals(ret.getString("DATA_TYPE"))){
    			table.setRequire("true");
    			table.setRequireClass("validate[required,custom[onlyNumberWide]]");
    			table.setMsg(table.getColumn_comment()+"只允许输入数字");
    		}else if("decimal".equals(ret.getString("DATA_TYPE"))){
    			table.setRequire("true");
    			table.setRequireClass("validate[required,custom[onlyNumberWide]]");
    			table.setMsg(table.getColumn_comment()+"只允许输入数字");
    		}else{
    			table.setRequire("false");
    			table.setRequireClass("");
    			table.setMsg(table.getColumn_comment()+"未知格式!请与管理员联系!");
    		}
    		
    		ts.add(table);
    	}
    	free(ret, ps, conn);
    	return ts;
    }
    /**
     * 获取表的主键列
     * @param sechma
     * @param tabelname
     * @return
     * @throws SQLException
     */
    public static List<GciTable> getTablesPKColumnByName(String sechma,String tabelname) throws SQLException{
    	List<GciTable> ts = new ArrayList<GciTable>();
    	conn = GciJdbcConnect.getConnection();
    	String SQL="SELECT t.TABLE_SCHEMA,t.TABLE_NAME,t.COLUMN_NAME,t.ORDINAL_POSITION,t.DATA_TYPE,t.CHARACTER_MAXIMUM_LENGTH,t.COLUMN_KEY,t.COLUMN_COMMENT,UCASE(LEFT(t.COLUMN_NAME,1)) AS upCo,SUBSTRING(t.COLUMN_NAME,2) AS lwCo  FROM information_schema.COLUMNS t WHERE t.COLUMN_KEY='pri' AND t.TABLE_NAME= ? AND  table_schema = ? ORDER BY t.ORDINAL_POSITION ASC ";
    	System.out.println(SQL);
    	ps = conn.prepareStatement(SQL);
    	ps.setString(1, tabelname);
    	ps.setString(2, sechma);
    	ret = ps.executeQuery();
    	GciTable table = null;
    	while(ret.next()){
    		table = new GciTable();
    		table.setTable_schema(ret.getString("TABLE_SCHEMA"));
    		table.setTable_name(ret.getString("TABLE_NAME").toLowerCase());
    		table.setClumn_name(ret.getString("COLUMN_NAME").toLowerCase());
    		table.setOrdinal_position(ret.getString("ORDINAL_POSITION"));//字段排序
    		table.setData_type(ret.getString("DATA_TYPE"));
    		table.setLength(Integer.parseInt(ret.getString("CHARACTER_MAXIMUM_LENGTH")==null?"11":ret.getString("CHARACTER_MAXIMUM_LENGTH")));//int类型没有默认长度，这里给默认11位
    		table.setColumn_key(ret.getString("COLUMN_KEY"));
    		table.setColumn_comment(ret.getString("COLUMN_COMMENT")==null?ret.getString("COLUMN_NAME"):ret.getString("COLUMN_COMMENT"));
    		table.setColumn_getset(ret.getString("upCo")+ret.getString("lwCo").toLowerCase());
    		table.setColumn_javatype(GciCodeTypeMap.getJavaTypeBySqlType().get(ret.getString("DATA_TYPE")));
    		table.setColumn_javaMybatintype(GciCodeTypeMap.getMySqlTypeBySqlType().get(ret.getString("DATA_TYPE")));
    		
    		ts.add(table);
    	}
    	free(ret, ps, conn);
    	return ts;
    }
    /**
     * jdbc方式根据表名\库名\字段\得到该字段所有信息
     * @param sechma 库名
     * @param tabelname 表名
     * @param column 列名
     * @return 表所有字段的详细信息
     * @throws SQLException
     */
    public static List<GciTable> getTablesColumsByName(String sechma,String tabelname,String column) throws SQLException{
    	List<GciTable> ts = new ArrayList<GciTable>();
    	conn = GciJdbcConnect.getConnection();
    	String SQL="SELECT t.TABLE_SCHEMA,t.TABLE_NAME,t.COLUMN_NAME,t.ORDINAL_POSITION,t.DATA_TYPE,t.CHARACTER_MAXIMUM_LENGTH,t.COLUMN_KEY,t.COLUMN_COMMENT,UCASE(LEFT(t.COLUMN_NAME,1)) AS upCo,SUBSTRING(t.COLUMN_NAME,2) AS lwCo  FROM information_schema.COLUMNS t WHERE t.TABLE_NAME= ? AND  t.table_schema = ? AND t.COLUMN_NAME= ? ORDER BY t.ORDINAL_POSITION ASC ";
    	ps = conn.prepareStatement(SQL);
    	ps.setString(1, tabelname.toUpperCase());
    	ps.setString(2, sechma.toUpperCase());
    	ps.setString(3, column);
    	ret = ps.executeQuery();
    	GciTable table = null;
    	while(ret.next()){
    		table = new GciTable();
    		table.setTable_schema(ret.getString("TABLE_SCHEMA"));
    		table.setTable_name(ret.getString("TABLE_NAME").toLowerCase());
    		table.setClumn_name(ret.getString("COLUMN_NAME").toLowerCase());
    		table.setOrdinal_position(ret.getString("ORDINAL_POSITION"));//字段排序
    		table.setData_type(ret.getString("DATA_TYPE"));
    		table.setLength(Integer.parseInt(ret.getString("CHARACTER_MAXIMUM_LENGTH")==null?"11":ret.getString("CHARACTER_MAXIMUM_LENGTH")));//int类型没有默认长度，这里给默认11位
    		table.setColumn_key(ret.getString("COLUMN_KEY"));
    		table.setColumn_comment(ret.getString("COLUMN_COMMENT")==null?ret.getString("COLUMN_NAME"):ret.getString("COLUMN_COMMENT"));
    		table.setColumn_getset(ret.getString("upCo")+ret.getString("lwCo").toLowerCase());
    		table.setColumn_javatype(GciCodeTypeMap.getJavaTypeBySqlType().get(ret.getString("DATA_TYPE")));
    		table.setColumn_javaMybatintype(GciCodeTypeMap.getMySqlTypeBySqlType().get(ret.getString("DATA_TYPE")));
    		
    		//自动生成验证信息相关字段熟悉
    		table.setDataType(GciCodeTypeMap.getValidateDateType().get(ret.getString("DATA_TYPE")));
    		if("varchar".equals(ret.getString("DATA_TYPE"))){
    			table.setRequire("true");
    			table.setRequireClass("validate[required,length[0,"+table.getLength()/2+"]]");
    			table.setMsg(table.getColumn_comment()+"为必填项,且最多"+table.getLength()/2+"个汉字");
    		}else if("int".equals(ret.getString("DATA_TYPE"))){
    			table.setRequire("true");
    			table.setRequireClass("validate[required,custom[onlyNumber],length[0,8]]");
    			table.setMsg(table.getColumn_comment()+"只能输入整数");
    		}else if("date".equals(ret.getString("DATA_TYPE"))){
    			table.setRequire("true");
    			table.setRequireClass("date validate[required,custom[date]]");
    			table.setMsg(table.getColumn_comment()+"只允许输入日期");
    		}else if("timestamp".equals(ret.getString("DATA_TYPE"))){
    			table.setRequire("true");
    			table.setRequireClass("date validate[required,custom[date]]");
    			table.setMsg(table.getColumn_comment()+"只允许输入日期");
    		}else if("double".equals(ret.getString("DATA_TYPE"))){
    			table.setRequire("true");
    			table.setRequireClass("validate[required,custom[onlyNumberWide]]");
    			table.setMsg(table.getColumn_comment()+"只允许输入数字");
    		}else if("decimal".equals(ret.getString("DATA_TYPE"))){
    			table.setRequire("true");
    			table.setRequireClass("validate[required,custom[onlyNumberWide]]");
    			table.setMsg(table.getColumn_comment()+"只允许输入数字");
    		}else{
    			table.setRequire("false");
    			table.setRequireClass("");
    			table.setMsg(table.getColumn_comment()+"未知格式!请与管理员联系!");
    		}
    		ts.add(table);
    	}
    	free(ret, ps, conn);
    	return ts;
    }
   
    // 释放连接
    public static void free(ResultSet rs, PreparedStatement st, Connection conn) {
        try {
            if (rs != null) {
                rs.close(); // 关闭结果集
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close(); // 关闭Statement
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close(); // 关闭连接
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
    	try {
    		List<GciTable> list = GciPropertyTable.getTablesPKColumnByName("HJFP2P", "console_index");
    		System.out.println(list.size());
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}
}
