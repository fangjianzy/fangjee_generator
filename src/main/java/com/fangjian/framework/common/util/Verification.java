package com.fangjian.framework.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**   
 * 类名称：Verification   </br>
 * 类描述： 后台验证工具类  </br>
 * 创建人：fangjian </br>
 * 创建时间：2016年1月18日 上午11:09:06   </br>
 * @version  0.1  </br>
 */
public class Verification {

	/**
	 * 后台验证用户名(6-18个以字母开头、可带数字、“_”、“.”的字串)
	 */
	public static boolean loginName(String userName) {
		 String regex = "^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){4,19}$";
		 return match(regex, userName);
	}
	/**
	 * 后台验证借款人所在城市和姓名(只包含中,英文长度在2-10位之间)
	 */
	public static boolean borrow(String city) {
		 String regex = "^[\u4e00-\u9fa5a-zA-Z]{2,10}$";
		 return match(regex, city);
	}
    /**
     * 后台验证密码(6-20位任意字符) 
     * @param userpass
     * @return
     */
	public static boolean loginPass(String userpass) {
		 String regex = "^[^\\s\u4e00-\u9fa5]{6,20}$";
		 return match(regex, userpass);
		}
	
	/**
	 * 后台验证手机号
	 * @param userphone
	 * @return
	 */
	public static boolean loginPhone(String userphone) {
		 String regex = "^(13|14|15|17|18)[0-9]{9}$";
		 return match(regex, userphone);
	}
	
	/**
	 * 验证邮箱
	 * @param userMail
	 * @return
	 */
	public static boolean loginMail(String userMail) {
		 String regex = "^([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$/";
		//  String regex = "\\w+@(\\w+.)+[a-z]{2,3}";
		 return match(regex, userMail);
	}
	 /**  
     * @param regex 正则表达式字符串  
     * @param str 要匹配的字符串  
     * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;  
     */    
	private static boolean match(String regex, String str)    
    {    
        Pattern pattern = Pattern.compile(regex);    
        Matcher matcher = pattern.matcher(str);    
        return matcher.matches();    
    }
	
	/**
	  * 验证邮箱
	  * @param email
	  * @return
	  */
	 public static boolean checkEmail(String email){
	  boolean flag = false;
	  try{
	    String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	    Pattern regex = Pattern.compile(check);
	    Matcher matcher = regex.matcher(email);
	    flag = matcher.matches();
	   }catch(Exception e){
	    flag = false;
	   }
	  return flag;
	 }
	
	 /**
	  * 验证手机号码
	  * @param mobiles
	  * @return
	  */
	 public static boolean checkMobileNumber(String mobileNumber){
	  boolean flag = false;
	  try{
	    Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
	    Matcher matcher = regex.matcher(mobileNumber);
	    flag = matcher.matches();
	   }catch(Exception e){
	    flag = false;
	   }
	  return flag;
	 }

}

