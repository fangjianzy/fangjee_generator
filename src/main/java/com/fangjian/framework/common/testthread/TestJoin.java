package com.fangjian.framework.common.testthread;
/**   
 * 类名称：TestJoin   </br>
 * 类描述：使用join(),当前线程结束后主线程才结束   </br>
 * 创建人：fangjian </br>
 * 创建时间：2016年5月19日 下午3:59:29   </br>
 * 修改人：fangjian </br>
 * 修改时间：2016年5月19日 下午3:59:29   </br>
 * 修改备注：   </br>
 * @version    </br>
 */
public class TestJoin {

	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName()+"主线程运行开始!");
		ThreadDemo mTh1=new ThreadDemo("A");
		ThreadDemo mTh2=new ThreadDemo("B");
		mTh1.start();
		mTh2.start();
		try {
			mTh1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			mTh2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+ "主线程运行结束!");
		//主线程一定会等子线程都结束了才结束
	}

}

