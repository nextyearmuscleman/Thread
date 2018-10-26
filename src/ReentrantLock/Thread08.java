/**
 * Author:  jixuelei
 */
package ReentrantLock;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jixuelei
 * 面试题：
 * 由1000张车票，每张票都有编号，现在又10个窗口去卖票，写一个模拟程序
 *      可能出现的问题：重复销售、超量销售
 *
 *      size()和remove()方法都不是同步的方法，多线程的执行过程可能会发生重复的问题，或者超量。
 */
public class Thread08 {
    //因为ArrayList不是线程安全的
    static List<String> lists = new ArrayList<>();
    static {
        for (int i = 0; i < 1000; i++) {
            lists.add("票编号是:"+i);
        }
    }
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                while(lists.size() >0){
                    System.out.println("销售了:"+ lists.remove(0));
                }
            }).start();
        }
    }
}
