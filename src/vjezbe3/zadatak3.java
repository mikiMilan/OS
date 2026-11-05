package vjezbe3;

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
        for (int i = 0; i < 100000; i++) {

            flag[name] = true;
            int otherThread = (name + 1) % 2;
            turn = otherThread;
            while (flag[otherThread] && turn==otherThread){}

            counter.increment();

            flag[name] = false;
        }
    }
}

public class zadatak3 {
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
