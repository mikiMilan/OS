package vjezbe4;

class MyRunnableZ2 implements Runnable{
    int name;
    Counter counter;

    MyRunnableZ2(int name, Counter counter){
        this.name = name;
        this.counter = counter;
    }

    @Override
    public void run(){
        int otherThread = (name + 1) % 2;
        for (int i = 0; i < 100000; i++) {
            counter.increment();
        }
    }
}

public class zadatak2 {
    public static void main(String[] args){
        Counter c = new Counter();
        Thread t1 = new Thread(new MyRunnableZ2(0, c));
        Thread t2 = new Thread(new MyRunnableZ2(1, c));

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        }catch (Exception e){
            System.out.println(e);
        }

        System.out.println(c.get());
    }
}


