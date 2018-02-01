package multi_thread;

public class RunnableDemo {
    static class Task implements Runnable {
        @Override
        public void run() {
            for(int i = 1; i < 100; i++){
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + ":" + i + "s...");
                } catch (InterruptedException e) {
                    System.out.println("Sleep fail");
                }
            }
        }
    }

    public static void main(String[] args){
        Task r = new Task();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();

        // Runnable can be implemented as Anonymous class
//        Thread t = new Thread(() -> {
//            for(int i = 1; i < 100; i++){
//                try {
//                    Thread.sleep(1000);
//                    System.out.println(Thread.currentThread().getName() + ":" + i + "s...");
//                } catch (InterruptedException e) {
//                    System.out.println("Sleep fail");
//                }
//            }
//        });
//        t.start();
    }
}
