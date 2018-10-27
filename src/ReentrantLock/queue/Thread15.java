/**
 * Author:  jixuelei
 */
package ReentrantLock.queue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author jixuelei
 * 1、学习Queue
 */
public class Thread15 {
    public static void main(String[] args) {
        /*
        ConcurrentLinkedQueue---同步的加锁队列
        1、是unbounded无界的线程安全的队列
         */
        Queue<String> str = new ConcurrentLinkedQueue<>();
        for (int i = 0; i < 10; i++) {
            str.offer("a"+i);//添加元素到队尾
        }
        System.out.println(str);
        System.out.println(str.size());

        System.out.println(str.poll());//返回队首的元素，并且删除。
        System.out.println(str.size());

        System.out.println(str.peek());//返回队首的元素，但是不删除
        System.out.println(str.size());
    }
}
