/**
 * Author:  jixuelei
 */
package ReentrantLock;

/**
 * @author jixuelei
 * https://www.cnblogs.com/xudong-bupt/p/3433643.html
 * 单例模式：
 */
public class Thread12 {
    private Thread12(){
        System.out.println("Singleton模式");
    }
    private static class inner{
        private static Thread12 sin = new Thread12();
    }
    private static Thread12 getSin(){
        return inner.sin;
    }
}
