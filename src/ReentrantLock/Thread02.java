/**
 * Author:  jixuelei
 */
package ReentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jixuelei
 * 使用ReentrantLock锁来代替Synchronized，需要手动的加锁|解锁。
 * 搭配try{...}catch{...}
 *
 * 这个例子中，需要2个线程互斥，那么2个线程使用同意把lock锁就可以了
 */
public class Thread02 {
    Lock lock = new ReentrantLock();

    void m1(){
            try {
                lock.lock();
                for (int i = 0; i < 10; i++) {
                    TimeUnit.SECONDS.sleep(1);

                    System.out.println(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
    }

    void m2(){
        /**1、设置了一个标志位，locked。
         * 该方法使用tryLock()尝试锁定，lock.tryLock(5,TimeUnit.SECONDS);
         * 构造函数的参数，是等待线程的最大时长，后一个是单位。（等待5s）；
         * 2、不管执行成功与否，后面的语句都执行。
         * 3、在finally中进行判断，是否该线程获取到了锁，如果获取到了锁，就释放。
         */
        boolean locked = false;
        try {
            locked = lock.tryLock(5,TimeUnit.SECONDS);
            System.out.println("线程2----"+locked);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if (locked)lock.unlock();
        }
    }
    public static void main(String[] args) {
        Thread02 t = new Thread02();
        new Thread(() -> t.m1()).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()-> t.m2()).start();
    }
}
