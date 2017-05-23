package com.fangjian.framework.core.cfg;

import java.util.Map;

/**   
 * 类名称：Frameworkcfg   </br>
 * 类描述：  代码生成器配置 </br>
 * 创建人：fangjian </br>
 * 创建时间：2016年1月18日 上午9:43:51   </br>
 * @version  0.1  </br>
 */
public class Frameworkcfg {

	/**
	 * 模板位置属性
	 */
	private String templete_path;
	/**
	 * 传递到模板的数据
	 */
	private Map<String, Object> datas;
	/**
	 * 生成文件路径
	 */
	private String path;
	/**
	 * 生成的文件名称
	 */
	private String filename;
	
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Map<String, Object> getDatas() {
		return datas;
	}

	public void setDatas(Map<String, Object> datas) {
		this.datas = datas;
	}

	public String getTemplete_path() {
		return templete_path;
	}

	public void setTemplete_path(String templete_path) {
		this.templete_path = templete_path;
	}

}

