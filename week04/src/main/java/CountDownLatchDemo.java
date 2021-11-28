import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class CountDownLatchDemo {
    public static void main(String[] args) {
        AtomicInteger result = new AtomicInteger(0);
        long start=System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(1);

        Thread t = new Thread(new CountDownLathTask(countDownLatch,result));
        t.start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result.get());

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

    }

    public static class CountDownLathTask implements Runnable{

        private CountDownLatch latch;
        private AtomicInteger result;
        public CountDownLathTask(CountDownLatch latch,AtomicInteger result){
            this.latch = latch;
            this.result = result;
        }
        @Override
        public void run() {
            this.result.addAndGet(sum());
            this.latch.countDown();
            System.out.println("任务结束！");
        }
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
