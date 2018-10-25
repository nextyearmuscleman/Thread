/**
 * Author:  jixuelei
 */
package Thread;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jixuelei
 */
public class Thread05{
    volatile List list = new ArrayList();

    public void add(Object o){
        list.add(o);
    }
    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        Thread05 t = new Thread05();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    t.add(new Object());
                    System.out.println("add:" + i);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if(t.size() == 5){
                        break;
                    }
                }
                System.out.println("t2结束");
            }
        },"t2").start();
    }
}
