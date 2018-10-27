/**
 * Author:  jixuelei
 */
package ReentrantLock.map_list_container;

import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * @author jixuelei
 * 1、学习CopyOnWrite容器
 * 读的效率高（不加锁），写的时候效率低
 */
public class Thread14 {

    public static void main(String[] args) {
        List<String> lists =
                //new ArrayList<>();//报错---java.lang.ArrayIndexOutOfBoundsException: 15
                //new Vector<>();//37--容器里面有100000个
               // new CopyOnWriteArrayList<>();//4772---容器里面有100000个
                Collections.synchronizedList(new ArrayList<>()); //总共耗时：31--容器里面有100000个
        Random r = new Random();
        Thread[] ths = new Thread[100];

        long start = System.currentTimeMillis();
        //加门闩
        CountDownLatch latch = new CountDownLatch(100);
        for (int i = 0; i < ths.length; i++) {
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        lists.add("test"+r.nextInt(1000));
                    }
                    latch.countDown();
                }
            };
            /*
            这个示例的实现方式与前面的不一样，显示的指定了Runnable接口，
            因此在循环添加线程的最后将Runnable接口的匿名内部类添加到Thread中。
             */
            ths[i] = new Thread(task);
        }

        for (int i = 0; i < ths.length; i++) {
                ths[i].start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("总共耗时："+(end-start));
        System.out.println("容器里面有"+lists.size()+"个");
    }
}
/*
写时复制容器的写操作是非常的费时间的，因为需要加锁，并且时复制一份新的Object[];将之前的数组coptOf().
public void add(int index, E element) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArray();
            int len = elements.length;
            if (index > len || index < 0)
                throw new IndexOutOfBoundsException("Index: "+index+
                                                    ", Size: "+len);
            Object[] newElements;
            int numMoved = len - index;
            if (numMoved == 0)
                newElements = Arrays.copyOf(elements, len + 1);
            else {
                newElements = new Object[len + 1];
                System.arraycopy(elements, 0, newElements, 0, index);
                System.arraycopy(elements, index, newElements, index + 1,
                                 numMoved);
            }
            newElements[index] = element;
            setArray(newElements);
        } finally {
            lock.unlock();
        }
 */
