/**
 * Author:  jixuelei
 */
package ReentrantLock.queue;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author jixuelei
 * ArrayBlockingQueue是一个有界队列，这个例子中给定的固定的capacity=10
 */
public class Thread17 {

    static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10);
    Random r = new Random();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
                strs.put("a"+i);
        }

        //strs.add("a");//超出了容量之后---IllegalStateException

        //strs.put("a");//之前的队列以及满了，所以这一步一直在阻塞-
        // waiting for space to become available if the queue is full.

        //System.out.println(strs.offer("a"));//false--并且offer()方法会理解返回结果不阻塞

        strs.offer("aaa",1, TimeUnit.SECONDS);//按时间阻塞，等待1s，过了这个时间之后就立即返回true|false
    }
}
