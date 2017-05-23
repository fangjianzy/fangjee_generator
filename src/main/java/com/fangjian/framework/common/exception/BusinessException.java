package com.fangjian.framework.common.exception;
/**   
 * 类名称：BusinessException   </br>
 * 类描述：自定义异常   </br>
 * 创建人：方坚 </br>
 * 创建时间：2016年1月20日 上午10:42:51   </br>
 * @version  0.1  </br>
 */
public class BusinessException extends Exception {  
	  
    private static final long serialVersionUID = 1L;  
  
    public BusinessException() {  
        // TODO Auto-generated constructor stub  
    }  
  
    public BusinessException(String message) {  
        super(message);  
        // TODO Auto-generated constructor stub  
    }  
  
    public BusinessException(Throwable cause) {  
        super(cause);  
        // TODO Auto-generated constructor stub  
    }  
  
    public BusinessException(String message, Throwable cause) {  
        super(message, cause);  
        // TODO Auto-generated constructor stub  
    }  
  
}  

