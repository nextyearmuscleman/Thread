/**
 * Author:  jixuelei
 */
package ReentrantLock;

/**
 * @author jixuelei
 * https://www.cnblogs.com/xudong-bupt/p/3433643.html
 * 单例模式：第四种写法，不用加锁，1处实现懒加载
 */
public class Thread12_Singleton {
    private Thread12_Singleton(){
        System.out.println("Singleton模式");
    }
    private static class inner{
        private static Thread12_Singleton sin = new Thread12_Singleton();//1
    }
    private static Thread12_Singleton getSin(){
        return inner.sin;
    }
}
