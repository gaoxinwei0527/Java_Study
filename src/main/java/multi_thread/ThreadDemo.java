package multi_thread;

public class ThreadDemo {
    static class Worker extends Thread{
        @Override
        public void run(){
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
        Worker worker1 = new Worker();
        Worker worker2 = new Worker();

        worker1.start();
        worker2.start();

        // if directly call worker.run(), then no other threads are created,
        // only main thread would run these two functions
        // we would see 'main:1s...'
//        worker1.run();
//        worker2.run();
    }
}
