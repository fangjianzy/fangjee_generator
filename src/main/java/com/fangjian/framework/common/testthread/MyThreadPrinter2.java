package com.fangjian.framework.common.testthread;
/**   
 * 类名称：MyThreadPrinter2   </br>
 * 类描述： wait 的使用  </br>
 * 创建人：fangjian </br>
 * 创建时间：2016年5月19日 下午4:14:27   </br>
 * 修改人：fangjian </br>
 * 修改时间：2016年5月19日 下午4:14:27   </br>
 * 修改备注：   </br>
 * @version    </br>
 */
public class MyThreadPrinter2 implements Runnable {   
	  
    private String name;   
    private Object prev;   
    private Object self;   
  
    private MyThreadPrinter2(String name, Object prev, Object self) {   
        this.name = name;   
        this.prev = prev;   
        this.self = self;   
    }   
  
    public void run() {   
        int count = 10;   
        while (count > 0) {   
            synchronized (prev){   
                synchronized (self) {   
                    System.out.print(name);   
                    count--;  
                    
                    self.notify();   
                }   
                try {   
                    prev.wait();   
                } catch (InterruptedException e) {   
                    e.printStackTrace();   
                }   
            }   
  
        }   
    }   
  
    public static void main(String[] args) throws Exception {   
        Object a = new Object();   
        Object b = new Object();   
        Object c = new Object();   
        MyThreadPrinter2 pa = new MyThreadPrinter2("A", c, a);   
        MyThreadPrinter2 pb = new MyThreadPrinter2("B", a, b);   
        MyThreadPrinter2 pc = new MyThreadPrinter2("C", b, c);   
           
           
        new Thread(pa).start();
        Thread.sleep(100);  //确保按顺序A、B、C执行
        new Thread(pb).start();
        Thread.sleep(100);  
        new Thread(pc).start();   
        Thread.sleep(100);  
        }   
} 

