package com.fangjian.framework.common.testthread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**   
 * 类名称：LockTest   </br>
 * 类描述：   </br>
 * 创建人：fangjian </br>
 * 创建时间：2016年5月20日 下午2:44:31   </br>
 * 修改人：fangjian </br>
 * 修改时间：2016年5月20日 下午2:44:31   </br>
 * 修改备注：   </br>
 * @version    </br>
 */
public class LockTest {  
    public static void main(String[] args) {  
        final Outputter1 output = new Outputter1();  
        new Thread() {  
            public void run() {  
                output.output("zhangsan");  
            };  
        }.start();        
        new Thread() {  
            public void run() {  
                output.output("lisi");  
            };  
        }.start();  
    }  
}  
class Outputter1 {  
    private Lock lock = new ReentrantLock();// 锁对象  
    public void output(String name) {  
        // TODO 线程输出方法  
        lock.lock();// 得到锁  
        try {  
            for(int i = 0; i < name.length(); i++) {  
                System.out.print(name.charAt(i));  
            }  
        } finally {  
            lock.unlock();// 释放锁  
        }  
    }  
}  

