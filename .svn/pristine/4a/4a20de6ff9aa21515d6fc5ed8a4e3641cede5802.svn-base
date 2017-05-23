package com.fangjian.framework.common.vo;

import java.io.Serializable;

/**   
 * 类名称：HttpResultVo   </br>
 * 类描述：   ajax回传给页面的json对象</br>
 * 创建人：方坚 </br>
 * 创建时间：2016年1月18日 上午11:02:38   </br>
 * @version   0.1 </br>
 */
public class HttpResultVo implements Serializable{

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since 1.0.0
	 */
	
	private static final long serialVersionUID = 1L;
	
	private int status;
	private Object data;
	private String text;
	private String errorInfo;
	
	public HttpResultVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HttpResultVo(int status, String text, String errorInfo) {
		super();
		this.status = status;
		this.text = text;
		this.errorInfo = errorInfo;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getErrorInfo() {
		return errorInfo;
	}
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

}

