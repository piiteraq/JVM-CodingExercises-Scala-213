package MultiThreading;


class CallMe {
    //synchronized
    void call(String msg) {
        System.out.print("[" + msg);
        try {
            Thread.sleep(1000);
        } catch(InterruptedException ex) {
            System.out.println("Interrupted");
        }
        System.out.println("]");
    }
}

class Caller implements Runnable {
    String msg_;
    CallMe target_;
    Thread t_;

    public Caller(CallMe targ, String s) {
        target_ = targ;
        msg_ = s;
        t_ = new Thread(this);
        t_.start();
    }

    public void run() {
        synchronized(target_) {
            target_.call(msg_);
        }
    }

    Thread getThread() { return t_; }

}

public class SyncDemo {

    public static void main(String[] args) {
        CallMe target = new CallMe();
        Caller ob1 = new Caller(target, "Hello");
        Caller ob2 = new Caller(target, "Synchronized");
        Caller ob3 = new Caller(target, "World");

        // Wait for threads to end
        try {
            ob1.getThread().join();
            ob2.getThread().join();
            ob3.getThread().join();
        } catch(InterruptedException ex) {
            System.out.println("Interrupted");
        }

    }

}
