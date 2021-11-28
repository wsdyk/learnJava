import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class SemaphoreDemo {

    static int Sum;

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(0);

        long start=System.currentTimeMillis();

        Runnable task = ()->{
            Sum=sum();
            semaphore.release();
        };

        Thread t = new Thread(task);
        t.start();


        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+Sum);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }



    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
