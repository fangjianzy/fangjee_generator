package com.fangjian.framework.common.util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 代码生成器-字符串常用工具类
 * @author 方坚
 */
public class Tools {
	/**
	 * 
	 * @param value
	 * @return 如果字符串不为空或者长度不为零返回true
	 */
	public static boolean isNotNull( String value ) {
		if( value == null || "".equals( value.trim()) || "null".equalsIgnoreCase(value) ) {
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @param value
	 * @return 如果字符串不为空或者长度不为零且能转换成数字格式则返回true
	 */
	public static boolean isDigital( String value ) {
		if( value == null || "".equals( value.trim()) || "null".equalsIgnoreCase(value)||!value.matches( "[\\d]+[\\.]?[\\d]*")) {
			return false;
		}
		return true;
	}
	/**
	 * 
	 * @return 返回当前系统时间，精确到毫秒
	 */
	public static Date getSysDateTime() {
		return new Timestamp( new Date().getTime() );
	}

	/**
	 * 方法说明：取当前日期(格式 20030801)
	 * 
	 */
	public static String getStrNowDate() {
		java.util.Date tdate = new java.util.Date();
		String nowtime = new Timestamp(tdate.getTime()).toString();
		nowtime = nowtime.substring(0, 10);
		String nowYear = nowtime.substring(0, 4);
		String nowMonth = nowtime.substring(5, 7);
		String nowDay = nowtime.substring(8, 10);
		String nowdate = nowYear + nowMonth + nowDay;
		return nowdate;

	}

	/**
	 * 方法说明：取当前日期 (格式 2003-08-01)
	 * 
	 */
	public static String getNowDate() {
		java.util.Date tdate = new java.util.Date();
		String nowtime = new Timestamp(tdate.getTime()).toString();
		nowtime = nowtime.substring(0, 10);
		return nowtime;
	}
	
	/**
	 * 格式化时间工具类
	 * @return 日期格式  指定字符串
	 */
	public static String formatDate( Date date, String format ) {
		SimpleDateFormat sf = new SimpleDateFormat( format );
		return sf.format(date);
	}
	
	public static boolean strInArray(String str,String[] strs){
		for (int i=0;i<strs.length;i++){
			if (str.equals(strs[i])){
				return true;
			}
		}
		return false;
	}
	
	/** 
	* 判断参数是否为0，为0则返回""，否则返回其值 
	* @param iSource 源字符串 
	* @return 字符串 
	*/ 
	public static String getString(int iSource) { 
	if (iSource == 0) { 
		return""; 
	} else { 
		return"" + iSource; 
		} 
	} 
	
	/** 
	* 转码：GBK ----> iso-8859-1 
	* @param s 转码字段 
	* @return 转码后的字段 
	*/ 

	public static String GBKtoISO(String s) { 
	try { 
		s = new String(s.getBytes("GBK"), "iso-8859-1"); 
	} catch (Exception e) { 
		System.out.println(e.getMessage());
	} 
		return s; 
	} 

	/** 
	* 转码：iso-8859-1 ----> GBK 
	* @param s 转码字段 
	* @return 转码后的字段 
	*/ 
	public static String ISOtoGBK(String s) { 
	try { 
		s = new String(s.getBytes("iso-8859-1"), "GBK"); 
	} catch (Exception e) { 
		System.out.println(e.getMessage());
	} 
		return s; 
	} 
	
	/** 
	* 判断参数是否为空，为空则返回0,不为空则返回其整型值 
	* @param sSource 源字符串 
	* @return 整型数 
	*/ 
	public int getInt(String sSource) { 
	 int iReturn = 0; 
	 if (sSource != null && !sSource.equals("")) { 
	   iReturn = Integer.parseInt(sSource); 
	 } 
	 return iReturn; 
	} 
	
	
	/**
	 * 
	* 方法名: isNum 
	* 方法描述: 正则表达式验证字符串是否为数字
	* 创建人：fangjian 
	* 创建时间：2016年1月18日 上午9:40:23   
	* 修改人：fangjian   
	* 修改时间：2016年1月18日 上午9:40:23   
	* 修改备注：   
	* 参数 @param str
	* 参数 @return
	* 返回类型 boolean
	* throws
	 */
	public static boolean isNum(String str){	
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");	
    }
	
	
	
	/***
	 * 输入字符串，输出字符串转换后的值，防止SQL注入
	 * @param 字符串
	 * @return 转换后的字符串
	 */
	public static String TransactSQLInjection(String str) {
		return str.replaceAll(".*([';]+|(--)+).*", "''").replaceAll("_", "LIWAN");
          // 我认为 应该是return str.replaceAll("([';])+|(--)+","");		 
   }
	
	/**
	 * 
	* 方法名: getDate 
	* 方法描述: 获取时间
	* 创建人：fangjian 
	* 创建时间：2016年1月18日 上午9:40:10   
	* 修改人：fangjian   
	* 修改时间：2016年1月18日 上午9:40:10   
	* 修改备注：   
	* 参数 @param sDate
	* 参数 @param dateFormat
	* 参数 @return
	* 返回类型 Date
	* throws
	 */
	public static Date getDate(String sDate, String dateFormat) {
		SimpleDateFormat fmt = new SimpleDateFormat(dateFormat);
		ParsePosition pos = new ParsePosition(0);

		return fmt.parse(sDate, pos);
	}
	
	/**
	 * 
	* 方法名: getCurrentDate 
	* 方法描述: 获取当前日期
	* 创建人：fangjian 
	* 创建时间：2016年1月18日 上午9:39:56   
	* 修改人：fangjian   
	* 修改时间：2016年1月18日 上午9:39:56   
	* 修改备注：   
	* 参数 @return
	* 返回类型 String
	* throws
	 */
	public static String getCurrentDate() {
		return getFormatDateTime(new Date(), "yyyy-MM-dd");
	}
	
	/**
	 * 根据给定的格式与时间(Date类型的)，返回时间字符串<br>
	 * 
	 * @param date
	 *            指定的日期
	 * @param format
	 *            日期格式字符串
	 * @return String 指定格式的日期字符串.
	 */
	public static String getFormatDateTime(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date).trim();
	}
	
	/**
	 * 首字母转小写
	 * @param s
	 * @return
	 */
	 public static String toLowerCaseFirstOne(String s){
		 s = repTableNameToJavaName(s);
	   if(Character.isLowerCase(s.charAt(0)))
	        return s;
	    else
	        return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
	 }
		
		
	/**
	 * 首字母转大写
	 * @param s
	 * @return
	 */
	  public static String toUpperCaseFirstOne(String s){
		  s = repTableNameToJavaName(s);
	     if(Character.isUpperCase(s.charAt(0)))
	            return s;
	      else
	            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
	  }
	  
	  /**
	   * 
	  * 方法名: repTableNameToJavaName 
	  * 方法描述: 将表名称转换成类名称，去掉特殊字符
	  * 创建人：fangjian 
	  * 创建时间：2016年1月18日 上午9:38:51   
	  * 修改人：fangjian   
	  * 修改时间：2016年1月18日 上午9:38:51   
	  * 修改备注：   
	  * 参数 @param tableName
	  * 参数 @return
	  * 返回类型 String
	  * throws
	   */
	  public static String repTableNameToJavaName(String tableName){
		  String s  = tableName.replace("_", "");
		  s = s.toLowerCase();
		  return s;
	  }
	  
	  /**
		 * 随机生成六位数验证码 
		 * @return
		 */
		public static int getRandomNum(){
			 Random r = new Random();
			 return r.nextInt(900000)+100000;//(Math.random()*(999999-100000)+100000)
		}
		
		/**
		 * 检测字符串是否不为空(null,"","null")
		 * @param s
		 * @return 不为空则返回true，否则返回false
		 */
		public static boolean notEmpty(String s){
			return s!=null && !"".equals(s) && !"null".equals(s);
		}
		
		/**
		 * 检测字符串是否为空(null,"","null")
		 * @param s
		 * @return 为空则返回true，不否则返回false
		 */
		public static boolean isEmpty(String s){
			return s==null || "".equals(s) || "null".equals(s);
		}
		
		/**
		 * 字符串转换为字符串数组
		 * @param str 字符串
		 * @param splitRegex 分隔符
		 * @return
		 */
		public static String[] str2StrArray(String str,String splitRegex){
			if(isEmpty(str)){
				return null;
			}
			return str.split(splitRegex);
		}
		
		/**
		 * 用默认的分隔符(,)将字符串转换为字符串数组
		 * @param str	字符串
		 * @return
		 */
		public static String[] str2StrArray(String str){
			return str2StrArray(str,",\\s*");
		}
		
		/**
		 * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
		 * @param date
		 * @return yyyy-MM-dd HH:mm:ss
		 */
		public static String date2Str(Date date){
			return date2Str(date,"yyyy-MM-dd HH:mm:ss");
		}
		
		/**
		 * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
		 * @param date
		 * @return
		 */
		public static Date str2Date(String date){
			if(notEmpty(date)){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					return sdf.parse(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return new Date();
			}else{
				return null;
			}
		}
		
		/**
		 * 按照参数format的格式，日期转字符串
		 * @param date
		 * @param format
		 * @return
		 */
		public static String date2Str(Date date,String format){
			if(date!=null){
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				return sdf.format(date);
			}else{
				return "";
			}
		}
		
		/**
		 * 把时间根据时、分、秒转换为时间段
		 * @param StrDate
		 */
		public static String getTimes(String StrDate){
			String resultTimes = "";
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    java.util.Date now;
		    
		    try {
		    	now = new Date();
		    	java.util.Date date=df.parse(StrDate);
		    	long times = now.getTime()-date.getTime();
		    	long day  =  times/(24*60*60*1000);
		    	long hour = (times/(60*60*1000)-day*24);
		    	long min  = ((times/(60*1000))-day*24*60-hour*60);
		    	long sec  = (times/1000-day*24*60*60-hour*60*60-min*60);
		        
		    	StringBuffer sb = new StringBuffer();
		    	//sb.append("发表于：");
		    	if(hour>0 ){
		    		sb.append(hour+"小时前");
		    	} else if(min>0){
		    		sb.append(min+"分钟前");
		    	} else{
		    		sb.append(sec+"秒前");
		    	}
		    		
		    	resultTimes = sb.toString();
		    } catch (ParseException e) {
		    	e.printStackTrace();
		    }
		    
		    return resultTimes;
		}
		
		
		/**
		 * 产生随机的六位数
		 * @return
		 */
		public static String getSix(){
			Random rad=new Random();
			
			String result  = rad.nextInt(1000000) +"";
			
			if(result.length()!=6){
				return getSix();
			}
			return result;
		}
		

		 
		/**
		 * 读取txt里的单行内容
		 * @param filePath  文件路径
		 */
		public static String readTxtFile(String fileP) {
			try {
				
				String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../";	//项目路径
				filePath = filePath.replaceAll("file:/", "");
				filePath = filePath.replaceAll("%20", " ");
				filePath = filePath.trim() + fileP.trim();
				
				String encoding = "utf-8";
				File file = new File(filePath);
				if (file.isFile() && file.exists()) { 		// 判断文件是否存在
					InputStreamReader read = new InputStreamReader(
					new FileInputStream(file), encoding);	// 考虑到编码格式
					BufferedReader bufferedReader = new BufferedReader(read);
					String lineTxt = null;
					while ((lineTxt = bufferedReader.readLine()) != null) {
						return lineTxt;
					}
					read.close();
				}else{
					System.out.println("找不到指定的文件,查看此路径是否正确:"+filePath);
				}
			} catch (Exception e) {
				System.out.println("读取文件内容出错");
			}
			return "";
		}
		
		
		public static void main(String[] args) {
			System.out.println(getSix());
		}
}

