package MultiThreading;

import java.util.concurrent.Semaphore;

class SQ {
    int n;

    // Start with consumer semaphore unavailable
    static Semaphore semCon = new Semaphore(0);
    static Semaphore semProd = new Semaphore(1);

    void get() {
        try {
            semCon.acquire();
        } catch(InterruptedException e) {
            System.out.println("interrupted ..");
        }
        System.out.println("Got: " + n);
        semProd.release();
    }

    void put(int n) {
        try {
            semProd.acquire();
        } catch(InterruptedException e) {
            System.out.println("interrupted ..");
        }

        this.n = n;
        System.out.println("Put: " + n);
        semCon.release();
    }
}

class SProducer implements Runnable {
    SQ q;

    SProducer(SQ q) {
        this.q = q;
        new Thread(this, "Producer").start();
    }

    public void run() {
        for (int i=0; i < 20; i++) q.put(i);
    }
}

class SConsumer implements Runnable {
    SQ q;

    SConsumer(SQ q) {
        this.q = q;
        new Thread(this, "Consumer").start();
    }

    public void run() {
        for (int i=0; i < 20; i++) q.get();
    }
}

public class ProdConsSemaphoreDemo {

    public static void main(String[] args) {
        SQ q = new SQ();
        new SConsumer(q);
        new SProducer(q);
    }
}
