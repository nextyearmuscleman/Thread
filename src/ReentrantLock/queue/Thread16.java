/**
 * Author:  jixuelei
 */
package ReentrantLock.queue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author jixuelei
 * LinkedBlockingQueue:阻塞队列，是无界的(unbounded)
 */
public class Thread16 {
    static BlockingQueue<String> strs = new LinkedBlockingQueue<>();
    static Random r = new Random();

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    //put()--如果队列以及满了线程就会阻塞
                    strs.put("a"+i);
                    TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //生产者线程
        },"p1").start();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                while (true){
                    try {
                        System.out.println(Thread.currentThread().getName()+
                                "take - "+strs.take());
                        //take();如果队列空了，就阻塞
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //消费者线程
            },"c"+i).start();
        }
    }
}
