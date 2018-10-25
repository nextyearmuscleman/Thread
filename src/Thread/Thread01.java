/**
 * Author:  jixuelei
 */
package Thread;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jixuelei
 * 试题：
 * 写一个容器，提供.add(),.size()使用两个线程，Thread1进行添加元素，
 * Thread2在容器添加到第5个元素的时候弹出通知。
 *      解决方法：1、线程2使用while(true){}进行等待，此方法效率很低。、
 *                2、使用.wait(),.notify()方法，
 *
 */
public class Thread01{
    volatile List list = new ArrayList();

    public void add(Object o){
        list.add(o);
    }
    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        Thread01 t = new Thread01();
        new Thread(()->{
          for(int i=0;i<10;i++){
              t.add(new Object());
              System.out.println("add:" +i);

              try {
                  Thread.sleep(1000);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
      },"t1").start();

        new Thread(() -> {
           while (true){
               if(t.size() == 5){
                   break;
               }
           }
            System.out.println("t2结束");
        },"t2").start();
/**
 * 1、针对同一个对象的多个线程执行，使用volatile可以保证可见性。
 * })
 */
    }
}
