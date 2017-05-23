package com.fangjian.framework.common.web;

import javax.servlet.http.HttpServletRequest;

import com.fangjian.framework.common.GlobalConstant;

/**
 * 
 * spring3 mvc 的日期传递[前台-后台]bug: org.springframework.validation.BindException
 * 的解决方式.包括xml的配置
 * @author fangjian
 */
public class BaseMybatisController{
	/**
	 * 
	* 方法名: getSession_pfuser 
	* 方法描述: 获取后台session中的用户
	* 创建人：fangjian 
	* 创建时间：2016年1月15日 上午10:34:21   
	* 修改人：fangjian   
	* 修改时间：2016年1月15日 上午10:34:21   
	* 修改备注：   
	* 参数 @param request
	* 参数 @return
	* 返回类型 Object
	* throws
	 */
	public Object getSession_pfuser(HttpServletRequest request){
		Object ob = request.getSession().getAttribute(GlobalConstant.SESSION_PFUSER);
		return ob;
	}
	
	/**
	 * 
	* 方法名: getSession_frount_pfuser 
	* 方法描述: 获取前台用户session
	* 创建人：fangjian 
	* 创建时间：2016年1月18日 上午9:29:06   
	* 修改人：fangjian   
	* 修改时间：2016年1月18日 上午9:29:06   
	* 修改备注：   
	* 参数 @param request
	* 参数 @return
	* 返回类型 Object
	* throws
	 */
	public Object getSession_middle_pfuser(HttpServletRequest request){
		Object ob = request.getSession().getAttribute(GlobalConstant.SESSION_MiDDLE_PFUSER);
		return ob;
	}
	
	/**
	 * 获取IP地址
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
