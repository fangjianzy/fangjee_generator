package com.fangjian.framework.core.table;

/**
 * 记录数据库表信息
 * @author 方坚
 */
public class GciTable {
	/*
	 * 库
	 */
	private String table_schema;
	//表名称
	private String table_name;
	//列名称
	private String clumn_name;
	//列的顺序号
	private String ordinal_position;
	//类型
	private String data_type;
	//长度
	private int length=11;
	//主键，外键
	private String column_key;
	//备注
	private String column_comment;
	//在生成getset方式用这个字段
	private String column_getset;
	//再生成getset方式用这个类型
	private String column_javatype;
	//在mybatis的配置文件中配置这个类型
	private String column_javaMybatintype;
	
	//自动生成validate.js的验证信息开始
	/**
	 * 字段在验证的时候输入的类型
	 */
	private String dataType;
	/**
	 * 验证失败的提示信息
	 */
	private String msg;
	/**
	 * 是否必填项目
	 */
	private String require;
	//自动生成validate.js的验证信息结束
	
	/**
	 * 自动生成QUI V3版本表单验证 input 的class
	 */
	private String requireClass;
	
	
	
	public String getRequireClass() {
		return requireClass;
	}
	public void setRequireClass(String requireClass) {
		this.requireClass = requireClass;
	}
	public String getDataType() {
		return dataType;
	}
	public String getRequire() {
		return require;
	}
	public void setRequire(String require) {
		this.require = require;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getColumn_javaMybatintype() {
		return column_javaMybatintype;
	}
	public void setColumn_javaMybatintype(String column_javaMybatintype) {
		this.column_javaMybatintype = column_javaMybatintype;
	}
	public String getColumn_javatype() {
		return column_javatype;
	}
	public void setColumn_javatype(String column_javatype) {
		this.column_javatype = column_javatype;
	}
	public String getColumn_getset() {
		return column_getset;
	}
	public void setColumn_getset(String column_getset) {
		this.column_getset = column_getset;
	}
	public String getColumn_comment() {
		return column_comment;
	}
	public void setColumn_comment(String column_comment) {
		this.column_comment = column_comment;
	}
	public String getTable_schema() {
		return table_schema;
	}
	public void setTable_schema(String table_schema) {
		this.table_schema = table_schema;
	}
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public String getClumn_name() {
		return clumn_name;
	}
	public void setClumn_name(String clumn_name) {
		this.clumn_name = clumn_name;
	}
	public String getOrdinal_position() {
		return ordinal_position;
	}
	public void setOrdinal_position(String ordinal_position) {
		this.ordinal_position = ordinal_position;
	}
	public String getData_type() {
		return data_type;
	}
	public void setData_type(String data_type) {
		this.data_type = data_type;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getColumn_key() {
		return column_key;
	}
	public void setColumn_key(String column_key) {
		this.column_key = column_key;
	}
	
}
