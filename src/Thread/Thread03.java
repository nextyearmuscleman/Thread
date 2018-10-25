/**
 * Author:  jixuelei
 */
package Thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author jixuelei
 * 第三种解决方法，使用CountDownLatch
 */
public class Thread03 {
        //添加volatile，使t2能够得到通知
         volatile List lists = new ArrayList();
         public void add(Object o){
             lists.add(o);
         }
         public int size(){
             return lists.size();
         }
         public static void main(String[] args) {
             Thread03 c = new Thread03();

             //new CountDownLatch(1);加上门闩；latch.countDown();此方法调用一次会将构造方法里面的次数
             //减1.等与0的时候门闩打开，等待的Thread1就可以继续执行。Thread1执行完毕之后，不需要额外的操作就可以
             //使Thread2继续执行。
            CountDownLatch latch = new CountDownLatch(1);
         new Thread(()->{// 线程一
            System.out.println("t2启动");
            if (c.size() != 5) {
            try {
                latch.await();
                // 也可以指定等待时间]
                //latch.await(5000, TimeUnit.MICROSECONDS);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("t2结束");
        }
    }," t2").start();

        new Thread(()->{ //线程二
        System.out.println("t1启动");
        for (int i = 0; i < 10; i++) {
            c.add(new Object());
            System.out.println("add " + i);

            if (c.size() == 5) {
                // 打开门阀，让t2得以执行
                latch.countDown();
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }, "t1").start();
}
}

