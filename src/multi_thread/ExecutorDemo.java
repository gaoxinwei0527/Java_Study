package multi_thread;

import java.util.concurrent.*;

public class ExecutorDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<Integer> future = service.submit(() -> 123);
        int res = future.get();
        System.out.println(res);
        service.shutdown();
    }
}
