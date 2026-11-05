package vjezbe3;

class MyThread extends Thread{
    String name;

    MyThread(String name){
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Ja sam tred " + name);
    }
}

public class primjer1 {

    public static void main(String[] args){
        MyThread t1 = new MyThread("1");
        MyThread t2 = new MyThread("2");

        t1.start();
        t2.start();
        System.out.println("Ja sam tred main");

        for (int i = 0; i < 10000000; i++) {
            i++;
        }

        System.out.println("Petlja je zavrsila");
    }
}
