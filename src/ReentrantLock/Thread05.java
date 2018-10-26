/**
 * Author:  jixuelei
 */
package ReentrantLock;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * @author jixuelei
 * 面试题：
 * 1、写一个固定容量的同步容器，拥有put()和get()和getCount()方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 *
 * 思路：使用wait()和notify()|notifyAll()
 * 1、2个注意的地方，在put()和get()方法上面，使用的是while(){}而不是if(){};
 * 原因在于，
 */
public class Thread05<T> {
    private final LinkedList<T> lists = new LinkedList<>();
    private final int MAX = 10;//容器的最大容量
    private int count = 0;

    public synchronized void put(T t){
        //当容器的元素个数等与最大值的时候，该对象线程的添加方法就wait()
        while (lists.size() == MAX){//这里面为什么用while而不是if
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lists.add(t);
        count++;
        /**
         * 通知消费者线程进行获取；
         * 并且唤醒了消费者线程以及生产者线程
         */
        this.notifyAll();
    }

    public synchronized T get(){
        T t = null;
        while (lists.size() == 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = lists.removeFirst();
        count--;
        //通知生产者线程添加
        this.notifyAll();
        return t;
    }

    public static void main(String[] args) {
        Thread05<String> t = new Thread05<>();
        //模拟消费者模式，获取容器中的元素
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

        //模拟生产者模式，进行添加元素
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                for (int j = 0; j < 25; j++) {
                    t.put(Thread.currentThread().getName()+"---"+j);
                }
            },"p"+i).start();

        }
    }
}

















