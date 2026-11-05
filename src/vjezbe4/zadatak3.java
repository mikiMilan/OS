package vjezbe4;


class SharedResource{

    private int value;
    private boolean isSet = false;

    public synchronized void set(int value){
        this.value = value;
        this.isSet = true;
        notify();
    }

    public  synchronized int get(){
        while (!isSet){
            try {
                wait();
            } catch (Exception e) {
                System.out.println("Greska u cekanju!!!");
            }
        }
        return this.value;
    }
}

class AThread extends Thread{

    public SharedResource sr;

    public AThread(SharedResource sr) {
        this.sr = sr;
    }

    @Override
    public void run(){
        System.out.println("Cekako da procitamo vrijednost...");
        System.out.println("Vrijednos je: "+sr.get());
    }
}

class BThread extends Thread{

    public SharedResource sr;

    public BThread(SharedResource sr) {
        this.sr = sr;
    }

    @Override
    public void run(){
        System.out.println("Spremam se da dodajelim vreijdnost.");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        sr.set(42);
        System.out.println("Vrijednost je dodjeljena!!!");
    }
}

public class zadatak3 {

    public static void main(String[] args) {
        SharedResource sr = new SharedResource();
        AThread a = new AThread(sr);
        BThread b = new BThread(sr);

        a.start();
        b.start();

        try {
            a.join();
            b.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Kraj :)");
    }

}
