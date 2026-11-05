package vjezbe3;

class MyRunnable implements Runnable{
    String name;

    MyRunnable(String name){
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Ja sam tred " + name);
    }
}

public class primjer2 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyRunnable("1"));
        Thread t2 = new Thread(new MyRunnable("2"));

        t1.start();
        t2.start();
    }
}
