/**
 * Author:  jixuelei
 */
package ReentrantLock;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * @author jixuelei
 */
public class Thread09 {
    static Vector<String> lists = new Vector<>();
    static {
        for (int i = 0; i < 1000; i++) {
            lists.add("票编号是:"+i);
        }
    }
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                while(lists.size() > 0){
                    /**
                     * 会出现异常，size()和remove()方法都是Vector容器里面的同步方法，是原子性的
                     * 但是两个方法之间无法保证原子性的问题
                     */
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("销售了:"+ lists.remove(0));
                }
            }).start();
        }
    }
}
