/**
 * Author:  jixuelei
 */
package ReentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jixuelei
 * ReentrantLock可以指定为公平锁
 * 打印结果：
 * Thread-1获得锁
 * Thread-2获得锁
 * Thread-1获得锁
 * Thread-2获得锁
 * Thread-1获得锁
 * Thread-2获得锁
 *
 *
 * 线程1和线程2交替进行。
 */
public class Thread04 extends Thread{
    Lock lock = new ReentrantLock(true);

    public void run(){
        for (int i = 0; i < 100; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+"获得锁");
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Thread04 t = new Thread04();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);

        t1.start();
        t2.start();
    }
}
