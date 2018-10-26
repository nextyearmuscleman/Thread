/**
 * Author:  jixuelei
 */
package ReentrantLock;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author jixuelei
 */
public class Thread10 {
    /**
     * 不会出问题，因为2没有再对容器进行操作了，仅在1处进行操作，2没有对容器的数量上的修改操作
     * ConcurrentLinkedQueue是并发容器。
     */
   static Queue<String> lists = new ConcurrentLinkedQueue<>();
    static {
        for (int i = 0; i < 1000; i++) {
            lists.add("票编号是:"+i);
        }
    }
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                while (true){
                    //poll()是CAS同步方法。
                    String s = lists.poll();//1
                    if (s == null) break;//2
                    System.out.println("销售了:"+ s);
                }
            }).start();
        }
    }
}
