package com.fangjian.framework.core.commom;
/**
 * 生成器主配置，生成器配置入口，唯一暴露的配置，所有开关应该都在这里配置
 * @author 方坚
 */
public class CodeCfg {
	//ssm=springmvc+spring+mybatis
	//ssh2=struts2+spring+hibernate
	private FrameworkType framework_type=FrameworkType.ssm;
	//基础包com.jiesai.framework
	private String basePackage;
	//iemsuser实体名称包名称，可以理解为模块名称，业务模块
	private String bizPackage;
	//要生成代码的表名称
	private String table_name;
	//中文名称,暂时备注使用
	private String code_name;
	//代码输出路径
	private String output_path;
	
	private String jspVsersion;
	
	private boolean isRestApiController = false;
	
	
	public boolean isRestApiController() {
		return isRestApiController;
	}
	public void setRestApiController(boolean isRestApiController) {
		this.isRestApiController = isRestApiController;
	}
	/**
	 * 实体名称，防止默认生成的实体重复，支持默认值,也支持用户自定义
	 */
	private String entity_name;
	
	/**
	 * 是否生成spring单元测试文件
	 */
	private String spring_junit_test;
	
	
	
	public String getJspVsersion() {
		return jspVsersion;
	}
	public void setJspVsersion(String jspVsersion) {
		this.jspVsersion = jspVsersion;
	}
	public String getSpring_junit_test() {
		return spring_junit_test;
	}
	public void setSpring_junit_test(String spring_junit_test) {
		this.spring_junit_test = spring_junit_test;
	}
	public String getEntity_name() {
		return entity_name;
	}
	public void setEntity_name(String entity_name) {
		this.entity_name = entity_name;
	}
	public String getOutput_path() {
		return output_path;
	}
	public void setOutput_path(String output_path) {
		this.output_path = output_path;
	}
	public String getBasePackage() {
		return basePackage;
	}
	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}
	
	public String getBizPackage() {
		return bizPackage;
	}
	public void setBizPackage(String bizPackage) {
		this.bizPackage = bizPackage;
	}
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public String getCode_name() {
		return code_name;
	}
	public void setCode_name(String code_name) {
		this.code_name = code_name;
	}
	public FrameworkType getFramework_type() {
		return framework_type;
	}
	public void setFramework_type(FrameworkType framework_type) {
		this.framework_type = framework_type;
	}
	
	
	
}
