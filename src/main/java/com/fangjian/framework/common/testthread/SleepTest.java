package com.fangjian.framework.common.testthread;
/**   
 * 类名称：SleepTest   </br>
 * 类描述：   </br>
 * 创建人：fangjian </br>
 * 创建时间：2016年5月20日 下午2:22:09   </br>
 * 修改人：fangjian </br>
 * 修改时间：2016年5月20日 下午2:22:09   </br>
 * 修改备注：   </br>
 * @version    </br>
 */
public class SleepTest {
	public static void main(String[] args) {
		// 创建共享对象
		Service service = new Service();
		// 创建线程
		SleepThread t1 = new SleepThread("t1", service);
		SleepThread t2 = new SleepThread("t2", service);
		// 启动线程
		t1.start();
		t2.start();
	}
	
}
class SleepThread extends Thread {
	private Service service;
	public SleepThread(String name, Service service) {
		super(name);
		this.service = service;
	}
	public void run() {
		service.calc();
	}
}
class Service {
	public synchronized void calc() {
		System.out.println(Thread.currentThread().getName() + "准备计算");
		System.out.println(Thread.currentThread().getName() + "感觉累了，开始睡觉");
		try {
			Thread.sleep(10000);// 睡10秒
		} catch (InterruptedException e) {
			return;
		}
		System.out.println(Thread.currentThread().getName() + "睡醒了，开始计算");
		System.out.println(Thread.currentThread().getName() + "计算完成");
	}
}

