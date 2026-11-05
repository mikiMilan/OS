package vjezbe5;

import java.util.concurrent.Semaphore;

class Counter{
    private int counter = 0;

    public int getCounter() {
        return counter;
    }

    public void setCounter() {
        this.counter++;
    }
}

class Tred extends Thread{
    static Semaphore s = new Semaphore(1);
    Counter c;

    Tred(Counter c){
        this.c = c;
    }

    @Override
    public void run() {
        for (int i=0;i<1000000;i++){
            try {
                s.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            c.setCounter();
            s.release();
        }
    }
}

public class zadatak2 {

    static void main() {
        Counter c = new Counter();

        Tred a = new Tred(c);
        Tred b = new Tred(c);

        a.start();
        b.start();

        try {
            a.join();
            b.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(c.getCounter());
    }
}
