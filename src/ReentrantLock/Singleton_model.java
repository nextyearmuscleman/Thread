/**
 * Author:  jixuelei
 */
package ReentrantLock;

/**
 * @author jixuelei
 */
public class Singleton_model {
    public static void main(String[] args) {
        Singleton.getSin();
    }
}

class Singleton {
    private static Singleton sin = new Singleton();    ///直接初始化一个实例对象

    private Singleton() {    ///private类型的构造函数，保证其他类对象不能直接new一个该对象的实例
    }

    public static Singleton getSin() {    ///该类唯一的一个public方法
        return sin;
    }
}

class Singleton2 {
    private static Singleton2 instance;
    private Singleton2 (){

    }
    public static synchronized Singleton2 getInstance(){    //对获取实例的方法进行同步
        if (instance == null)
            instance = new Singleton2();
        return instance;
    }
}

class Singleton3 {
    private static Singleton3 instance;
    private Singleton3 (){
    }
    public static Singleton3 getInstance(){    //对获取实例的方法进行同步
        if (instance == null){
            synchronized(Singleton.class){
                if (instance == null)
                    instance = new Singleton3();
            }
        }
        return instance;
    }

}