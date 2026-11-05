package vjezbe3;

class Counter{
    private int count = 0;

    public void increment(){  count++;  }
    public int get() {  return count;  }
}

class MyRunnableZ2 implements Runnable{

    String name;
    Counter counter;

    MyRunnableZ2(String name, Counter counter){
        this.name = name;
        this.counter = counter;
    }

    @Override
    public void run(){
        for (int i = 0; i < 100000; i++) {
            counter.increment();
        }
    }
}

public class zadatak2 {
    public static void main(String[] args){
        Counter c = new Counter();
        Thread t1 = new Thread(new MyRunnableZ2("1", c));
        Thread t2 = new Thread(new MyRunnableZ2("2", c));

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
