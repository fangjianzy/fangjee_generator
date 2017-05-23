package com.fangjian.framework.common.testthread;
/**   
 * 类名称：ThreadPoolTest   </br>
 * 类描述：   </br>
 * 创建人：fangjian </br>
 * 创建时间：2016年5月20日 下午2:38:32   </br>
 * 修改人：fangjian </br>
 * 修改时间：2016年5月20日 下午2:38:32   </br>
 * 修改备注：   </br>
 * @version    </br>
 */
import java.util.concurrent.ExecutorService;  
import java.util.concurrent.Executors;  
public class ThreadPoolTest {  
    public static void main(String[] args) {  
        ExecutorService threadPool = Executors.newSingleThreadExecutor();  
        for(int i = 1; i < 5; i++) {  
            final int taskID = i;  
            threadPool.execute(new Runnable() {  
                public void run() {  
                    for(int i = 1; i < 5; i++) {  
                        try {  
                            Thread.sleep(20);// 为了测试出效果，让每次任务执行都需要一定时间  
                        } catch (InterruptedException e) {  
                            e.printStackTrace();  
                        }  
                        System.out.println("第" + taskID + "次任务的第" + i + "次执行");  
                    }  
                }  
            });  
        }  
        threadPool.shutdown();// 任务执行完毕，关闭线程池  
    }  
} 