package vjezbe4;

class MyRunnableZ3 implements Runnable{

    static volatile boolean[] flag = new boolean[2];
    static volatile int turn = 0;

    int name;
    Counter counter;

    MyRunnableZ3(int name, Counter counter){
        this.name = name;
        this.counter = counter;
    }

    @Override
    public void run(){
        int otherThread = (name + 1) % 2;
        for (int i = 0; i < 100000; i++) {

            flag[name] = true;
            while (flag[otherThread])
                if( turn == otherThread){
                    flag[name] = false;
                    while (turn == otherThread){ Thread.yield(); }
                    flag[name] = true;
                }

            counter.increment();

            turn = otherThread;
            flag[name] = false;


        }
    }
}

public class zadatak1 {
    public static void main(String[] args){
        Counter c = new Counter();
        Thread t1 = new Thread(new MyRunnableZ3(0, c));
        Thread t2 = new Thread(new MyRunnableZ3(1, c));

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

