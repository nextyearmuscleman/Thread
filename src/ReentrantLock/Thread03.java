/**
 * Author:  jixuelei
 */
package ReentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jixuelei
 * 1、当线程1执行的时候一直步释放锁，线程2等待是没有用的，此时想打断的话，使用
 * lockInterruptibly(),加锁方法，并且该方法是可以被打断的。
 * 执行效果：
 * java.lang.InterruptedException
 */
public class Thread03 {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        new Thread(()->{
            lock.lock();
            System.out.println("线程1 启动");
            try {
                //相当于线程1一直不释放锁
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                System.out.println("线程1 结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }).start();

        Thread t2 = new Thread(()->{
            boolean locked = false;
            try {
                //线程2虽然在等待着线程1释放锁，但是这个lockInterruptibly()是可以被打断的。
                //因为线程1一直不释放，所以想打断线程2
                //用lock.lock()；这个方法是无法打断的。
               lock.lockInterruptibly();
               locked = ((ReentrantLock) lock).isLocked();
               System.out.println("这是线程2获取到锁了吗？"+locked);
                System.out.println("线程2 启动");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                System.out.println("我被打断了");
                e.printStackTrace();
            }finally {
                if (locked) lock.unlock();
            }
        });
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //尝试打断线程2的等待。
        t2.interrupt();
    }
}
