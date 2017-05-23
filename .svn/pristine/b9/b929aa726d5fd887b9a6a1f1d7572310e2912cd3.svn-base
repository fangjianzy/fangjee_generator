package com.fangjian.framework.common.testthread;
/**   
 * 类名称：TraditionalThreadSynchronized   </br>
 * 类描述：   </br>
 * 创建人：fangjian </br>
 * 创建时间：2016年5月20日 下午2:46:43   </br>
 * 修改人：fangjian </br>
 * 修改时间：2016年5月20日 下午2:46:43   </br>
 * 修改备注：   </br>
 * @version    </br>
 */
public class TraditionalThreadSynchronized {

	public static void main(String[] args) {  
        final Outputter output = new Outputter();  
        new Thread() {  
            public void run() {  
                output.output("zhangsan");  
            }  
        }.start();        
        new Thread() {  
            public void run() {  
                output.output("lisi");  
            }  
        }.start();  
    }  
}  
class Outputter {  
    public void output(String name) {  
        // TODO 为了保证对name的输出不是一个原子操作，这里逐个输出name的每个字符  
        for(int i = 0; i < name.length(); i++) {  
            System.out.print(name.charAt(i));  
            // Thread.sleep(10);  
        }  
    }  

}

