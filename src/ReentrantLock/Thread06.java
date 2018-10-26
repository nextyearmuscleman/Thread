/**
 * Author:  jixuelei
 */
package ReentrantLock;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jixuelei
 * 面试题：
 *  * 1、写一个固定容量的同步容器，拥有put()和get()和getCount()方法，
 *  * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 *
 *  第二种解决方法，使用Condition
 *  1、Condition与ReentrantLock的关系就类似于synchronized与Object.wait()/signal()
 * 2、await()方法会使当前线程等待，同时释放当前锁，当其他线程中使用signal()时或者signalAll()方法时，
 * 3、线程会重新获得锁并继续执行。或者当线程被中断时，也能跳出等待。这和Object.wait()方法很相似。
 * 4、awaitUninterruptibly()方法与await()方法基本相同，但是它并不会再等待过程中响应中断。 不能被打断，和lockinterruptibly()不一样
 * 5、singal()方法用于唤醒一个在等待中的线程。相对的singalAll()方法会唤醒所有在等待中的线程。
 * 这和Obejct.notify()方法很类似。
 */
public class Thread06<T> {
    private final LinkedList<T> lists = new LinkedList<>();
    private final int MAX = 10;
    private int count;

    private Lock lock = new ReentrantLock();
    private Condition procucer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    public void put(T t){
        try {
            lock.lock();
            while (lists.size() == MAX){
                procucer.await();
            }
            lists.add(t);
            count++;
            //叫醒消费者线程，比notifyAll()方法好的地方在于，能够较为准确的唤醒指定的线程
            consumer.signalAll();
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public T get(){
        T t = null;
        lock.lock();
            try {
                while (lists.size() == 0) {
                    consumer.await();
                }
               t = lists.removeFirst();
                count--;
                //唤醒生产者线程。唤醒的粒度比notifyAll()更细。
                procucer.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
        }
        return t;
    }

    public static void main(String[] args) {
        Thread06<String> t = new Thread06();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 5; j++) {
                    System.out.println(t.get());
                }
            },"c"+i).start();

        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                for (int j = 0; j < 25; j++) {
                    t.put(Thread.currentThread().getName()+"---"+j);
                }
            },"p"+i).start();
        }
    }
}
