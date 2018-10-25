/**
 * Author:  jixuelei
 */
package Thread;

/**
 * @author jixuelei
 * 问题：写一个程序，在main线程中启动100个线程，这100个线程完成之后，main线程打印"完成“
 *      思路:join()   CountDownLatch
 */
public class Thread04 extends Thread{

    public void run(){
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        Thread04 t = new Thread04();

        for (int i=0;i<10;i++){
            Thread t1 = new Thread(t);
            Thread.sleep(100);
            t1.start();
            t1.join();
        }
        System.out.println("完成");
    }
}
