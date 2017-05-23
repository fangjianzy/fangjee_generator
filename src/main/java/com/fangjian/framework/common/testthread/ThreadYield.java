package com.fangjian.framework.common.testthread;
/**   
 * 类名称：ThreadYield   </br>
 * 类描述：   </br>
 * 创建人：fangjian </br>
 * 创建时间：2016年5月19日 下午4:06:32   </br>
 * 修改人：fangjian </br>
 * 修改时间：2016年5月19日 下午4:06:32   </br>
 * 修改备注：   </br>
 * @version    </br>
 */
public class ThreadYield extends Thread{
    public ThreadYield(String name) {
        super(name);
    }
 
    @Override
    public void run(){
        for (int i = 1; i <= 50; i++) {
            System.out.println("" + this.getName() + "-----" + i);
            // 当i为30时，该线程就会把CPU时间让掉，让其他或者自己的线程执行（也就是谁先抢到谁执行）
            
            if (i ==30) {
                this.yield();
                System.out.println("当i为30时，该线程就会把CPU时间让掉，"+this.getName()+"抢到优先执行机会。");
            }
        }
	
    }
    
    public static void main(String[] args) {
		ThreadYield yt1 = new ThreadYield("张三");
    	ThreadYield yt2 = new ThreadYield("李四");
        yt1.start();
        yt2.start();
	}
}

