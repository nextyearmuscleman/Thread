/**
 * Author:  jixuelei
 */
package ReentrantLock;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author jixuelei
 */
public class Thread11 {
    static List<String> lists = new LinkedList<>();
    static {
        for (int i = 0; i < 1000; i++) {
            lists.add("票编号是:"+i);
        }
    }
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                /**
                 *是没有问题的，
                 * 两个操作容器数量的方法都放在了synchronized方法中，lists对象被锁住了。
                 */
                    synchronized (lists){
                        while (lists.size() > 0){
                        try {
                            TimeUnit.MILLISECONDS.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("销售了:"+ lists.remove(0));
                    }
                }
            }).start();
        }
    }
}
