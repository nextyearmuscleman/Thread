/**
 * Author:  jixuelei
 */
package ReentrantLock;

import java.util.concurrent.TimeUnit;

/**
 * @author jixuelei
 *Lambda写法
 * new Thread(lock::m2).start();
 * 1、new Thread(()->lock1.m()).start();
 *
 * 2、new Thread(()->{
 *     lock.m2();
 *     }).start();
 *
 *  3、new Thread(new Runnable(){
 *          public void run(){
 *              lock.m2();
 *          }
 *  },"t2").start();
 */

/**
 * 这个例子比较简单，但是给了我一个启发，因为之前我疑惑能不能在同一个类中写多个不同的
 * run(),一直找不到头绪。看完下面的代码给了我思路。
 * 1、在类中有不同的m1()、m2()两个方法，分别处理不同的逻辑，
 * 2、创建两个线程，获取的是同一个锁，在这里也就是this。（lock）在两个线程中run()调用不同的
 * 方法，达到了我之前的想法。
 */
public class Thread01 {
    synchronized void m1(){
        for (int i = 0; i < 10; i++){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }
    }

    synchronized void m2(){
        System.out.println("m2-----");
    }

    public static void main(String[] args) {
        Thread01 lock = new Thread01();

        new Thread(lock::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(lock::m2).start();
    }
}
