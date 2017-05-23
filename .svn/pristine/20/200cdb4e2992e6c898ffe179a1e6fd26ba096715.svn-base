package com.fangjian.framework.common.testthread;
/**   
 * 类名称：ThreadDemo   </br>
 * 类描述：不使用join(),主线程先结束，子线程后结束</br>
 * 创建人：fangjian </br>
 * 创建时间：2016年5月19日 下午3:54:43   </br>
 * 修改人：fangjian </br>
 * 修改时间：2016年5月19日 下午3:54:43   </br>
 * 修改备注：   </br>
 * @version    </br>
 */
public class ThreadDemo extends Thread {
	private String name;
	
    public ThreadDemo(String name) {
       super(name);
       this.name=name;
    }
    
    @Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " 线程运行开始!");
        for (int i = 0; i < 5; i++) {
            System.out.println("子线程"+name + "运行 : " + i);
            try {
                sleep((int) Math.random() * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " 线程运行结束!");
	}
    
    public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName()+"主线程运行开始!");
		ThreadDemo mTh1=new ThreadDemo("A");
		ThreadDemo mTh2=new ThreadDemo("B");
		mTh1.start();
		mTh2.start();
		System.out.println(Thread.currentThread().getName()+ "主线程运行结束!");
		//没有join的情况下，主线程比子线程结束得早
	}

}

