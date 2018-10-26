/**
 * Author:  jixuelei
 */
package ReentrantLock;

import java.util.concurrent.TimeUnit;

/**
 * @author jixuelei
 * 学习ThreadLocal--->线程局部变量
 */
public class Thread07 {
    /**
     * @param args
     *     volatile static Person p =new Person();加不加volatile打印的都是jxl，2修改之后的name
     *     使用ThreadLocal线程局部变量-->使得线程在自己的域内拷贝了一份数据，对别的线程不可见，即使
     *     是使用了volatile。用空间换时间。
     */
    static ThreadLocal<Person> tl = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //t1null
            System.out.println(Thread.currentThread().getName() + tl.get());
        },"t1").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tl.set(new Person());
            System.out.println(tl.get());//ReentrantLock.Person@48bcc212
        },"t2").start();
    }

}
class Person{
    String name = "cy";
}
