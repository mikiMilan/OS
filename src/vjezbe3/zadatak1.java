package vjezbe3;

class MyRunnableZ1 implements Runnable{

    String name;

    MyRunnableZ1(String name){
        this.name = name;
    }

    @Override
    public void run(){
        for (int i = 1; i < 6; i++) {
            System.out.println("Tred: " + name + " broj> " + i);

            try {
                Thread.sleep(500);
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }
}

public class zadatak1 {
    public static void main(String[] args){
        Thread t1 = new Thread(new MyRunnableZ1("1"));
        Thread t2 = new Thread(new MyRunnableZ1("2"));

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        }catch (Exception e){
            System.out.println(e);
        }

        System.out.println("Kraj!!!");
    }
}
