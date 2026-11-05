package vjezbe4;

import java.util.Random;

class Automobil extends Thread{

    int[][] matrix;
    int brojTrkaca = 3;
    static volatile boolean krajTrke = false;
    static volatile int pobjednik = -1;

    int id;
    String naziv;

    public Automobil(int[][] matrix, int id, String naziv) {
        this.matrix = matrix;
        this.id = id;
        this.naziv = naziv;
    }

    private static synchronized boolean pobjeda(int id){

        if (krajTrke == false) {
            pobjednik = id;
            krajTrke = true;
            return true;
        }
        return false;
    }

    private static boolean isEnd(){
        return krajTrke;
    }

    @Override
    public void run() {
        int pozicija = 0;
        for (pozicija = 0; pozicija<15; pozicija++){

            if (pozicija==14){

                if (pobjeda(this.id))
                    System.out.println("Auto " + naziv + " je pobjedio u trci");

            }else {
                try {
                    if (matrix[this.id][pozicija] == 1) {
                        Thread.sleep(5000);
                        System.out.println("Auto " + naziv + " je stigao na STOP");
                    } else
                        Thread.sleep(2000);
                } catch (Exception e) {
                    System.out.println(e);
                }
                if(isEnd()){
                    System.out.println("Auto " + naziv + " je zavrsio trku na poziciji  " + pozicija);
                    break;
                }
                System.out.println("Auto " + naziv + " je prosao poziciju " + pozicija);

            }



        }
    }
}



public class zadatak4 {
    static void main() {
        int[][] staza = new int[3][15];
        Random rand = new Random();

        for(int i=0; i<4; i++){
            int x = rand.nextInt(3);
            int y = rand.nextInt(14);
            if (staza[x][y] == 1) i--;
            staza[x][y] = 1;
        }

        Automobil a1 = new Automobil(staza, 0, "Jugo");
        Automobil a2 = new Automobil(staza, 1, "Fico");
        Automobil a3 = new Automobil(staza, 2, "Ferari");

        a1.start();
        a2.start();
        a3.start();

        try {
            a1.join();
            a2.join();
            a3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }




}
