package com.fangjian.framework.common.testthread;
/**   
 * 类名称：TimerTest   </br>
 * 类描述：   </br>
 * 创建人：fangjian </br>
 * 创建时间：2016年5月20日 下午2:33:37   </br>
 * 修改人：fangjian </br>
 * 修改时间：2016年5月20日 下午2:33:37   </br>
 * 修改备注：   </br>
 * @version    </br>
 */
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
public class TimerTest {
	static class MyTimerTask1 extends TimerTask {
		public void run() {
			System.out.println("爆炸！！！");
			new Timer().schedule(new MyTimerTask2(), 2000);
		}
	}
	static class MyTimerTask2 extends TimerTask {
		public void run() {
			System.out.println("爆炸！！！");
			new Timer().schedule(new MyTimerTask1(), 3000);
		}
	}
	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new MyTimerTask2(), 2000);
		while(true) {
			System.out.println(new Date().getSeconds());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}


