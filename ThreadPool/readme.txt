1、Executor接口--》void execute（Runnable command）

2、ExecutorService  extends Executor
        Future submit(Runnable task)  Callable有返回值
        Future submit(Callable task)
        void  execute(Runnable task)

3、Executors工具类，能够产生线程池

4、六种线程池
    4.1 newFixedThreadPool--在初始化时固定了容量的线程池
    4.2 newCachedThreadPool--可缓存线程池，如果线程池长度超过处
    理需要，可灵活回收空闲线程，若无可回收，则新建线程
    4.3 newScheduledThreadPool---支持定时及周期性任务执行，初始化时提供corePoolSize
    4.4 newSingleThreadExecutor---一个单一的工作线程
    4.5 newWorkStealingPool--线程池中的线程维护一个队列，自己的队列
    执行完毕之后，可以去其他的工作队列中去执行
    4.6 ForkJoinPool--分支+结合。使用迭代
