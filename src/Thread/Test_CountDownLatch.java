/**
 * Author:  jixuelei
 */
package ReentrantLock;

import java.util.concurrent.CountDownLatch;

/**
 * @author jixuelei
 * 用CountDownLatch实现100个线程先打印“工作”，之后主线程打印“完成”
 */
public class Test_CountDownLatch {

    public static void main(String[] args) {
        Test_CountDownLatch t = new Test_CountDownLatch();
        CountDownLatch latch = new CountDownLatch(100);

        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName());
                latch.countDown();
            }).start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("完成");
    }
}
