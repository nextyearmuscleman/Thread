/**
 * Author:  jixuelei
 */
package Thread;

/**
 * @author jixuelei
 */
public class This {

    public void  m(){
        //这个this指代的是--->在main主程序在This类实例化出来的那个对象在堆中的引用
        //代表一个指针，锁住的是对象！！对象！对象！！！
        synchronized (this){

        }
    }
}
