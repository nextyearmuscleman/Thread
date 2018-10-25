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
 
 4中加锁方法的总结：
 假如线程A和线程B使用同一个锁LOCK，此时线程A首先获取到锁LOCK.lock()，并且始终持有不释放。如果此时B要去获取锁，有四种方式：

    1、LOCK.lock(): 此方式会始终处于等待中，即使调用B.interrupt()也不能中断，除非线程A调用LOCK.unlock()释放锁。
    2、LOCK.lockInterruptibly(): 此方式会等待，但当调用B.interrupt()会被中断等待，并抛出InterruptedException异常，
    否则会与lock()一样始终处于等待中，直到线程A释放锁。
    3、LOCK.tryLock(): 该处不会等待，获取不到锁并直接返回false，去执行下面的逻辑。
    4、LOCK.tryLock(10, TimeUnit.SECONDS)：该处会在10秒时间内处于等待中，但当调用B.interrupt()会被中断等待，
    并抛出InterruptedException。10秒时间内如果线程A释放锁，会获取到锁并返回true，否则10秒过后会获取不到锁并返回
    false，去执行下面的逻辑。

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
            /**
            没有写加锁之后的处理逻辑，这个仅是尝试获取锁，等待5s之后执行后面的语句。不影响执行。
            */
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
