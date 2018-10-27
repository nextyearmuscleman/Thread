/**
 * Author:  jixuelei
 */
package ReentrantLock.map_list_container;

import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * @author jixuelei
 * http://blog.csdn.net/sunxianghuang/article/details/52221913
 * 问题：多线程的情况下，哪一个容器的效率更高。
 */
public class Thread13 {
    public static void main(String[] args) {

       //Map<String, String> map = new HashMap<>();//280
        Map<String, String> map = Collections.synchronizedMap(new HashMap<>());//385
//        Map<String, String> map2 = new Hashtable<>();//416
//        Map<String, String> map3 = new ConcurrentHashMap<>();//381
//        Map<String, String> map4 = new ConcurrentSkipListMap<>();//311

        Random r = new Random(10);
        Thread[] ths = new Thread[100];

        CountDownLatch latch = new CountDownLatch(100);
        long start = System.currentTimeMillis();
        for (int i = 0; i < ths.length; i++) {
            ths[i] = new Thread(()->{
                for (int j = 0; j < 10000; j++)
                    map.put("a"+r.nextInt(10000),"a"+r.nextInt(10000));
                   //一个线程添加完10000个数之后latch减去1
                latch.countDown();
            });
        }
        //lambda表达式写foreach表达式。t表示的是每一个线程
        Arrays.asList(ths).forEach(t->t.start());

//        for (int i = 0; i < ths.length; i++) {
//            ths[i].start();
//        }

        //在主线程上面调用latch.await();此时主线程等待，知道CountDownLatch减少为1
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("总共耗时--"+(end-start));
    }

}
