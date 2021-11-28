import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        AtomicInteger result = new AtomicInteger(0);
        long start=System.currentTimeMillis();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);


        Thread t = new Thread(new CyclicBarrierTask(cyclicBarrier,result));
        t.start();
        Thread t2 = new Thread(new CyclicBarrierTask(cyclicBarrier,result));
        t2.start();



        try {
            cyclicBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result.get());

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

    }

    public static class CyclicBarrierTask implements Runnable{

        private CyclicBarrier cyclicBarrier;
        private AtomicInteger result;
        public CyclicBarrierTask(CyclicBarrier cyclicBarrier,AtomicInteger result){
            this.cyclicBarrier = cyclicBarrier;
            this.result = result;
        }
        @Override
        public void run() {
            this.result.addAndGet(sum());
            System.out.println(cyclicBarrier.getNumberWaiting());
            try {

                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
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
