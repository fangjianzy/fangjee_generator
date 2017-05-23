package com.fangjian.framework.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**   
 * 类描述：代码生成器获取IP地址，或者目录工具类   </br>
 * 创建人：fangjian </br>
 * 创建时间：2016年1月18日 上午11:16:52   </br>
 * @version  0.1  </br>
 */
public class PublicUtil {

	public static void main(String[] args) {
		System.out.println("本机的ip=" + PublicUtil.getIp());
	}
	
	public static String getPorjectPath(){
		String nowpath = "";
		nowpath=System.getProperty("user.dir")+"/";
		return nowpath;
	}
	
	/**
	 * 获取本机ip
	 * @return
	 */
	public static String getIp(){
		String ip = "";
		try {
			InetAddress inet = InetAddress.getLocalHost();
			ip = inet.getHostAddress();
			//System.out.println("本机的ip=" + ip);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		return ip;
	}

}

