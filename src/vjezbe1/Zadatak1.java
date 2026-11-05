package vjezbe1;

public class Zadatak1 {

    public static void run(){
        int n = 3;

        int[] B = {4,2,1};
        int[] W = {0,0,0};
        int[] T = {0,0,0};
        int[] C = {0,0,0};

        // C[i]?
        C[0] = B[0];
        for (int i = 1; i < n; i++)
            C[i] = C[i-1] + B[i];

        for (int i = 0; i < n; i++)
            T[i] = C[i];

        for (int i = 0; i < n; i++)
            W[i] = T[i] - B[i];

        System.out.println("Vrijeme zavrsetka>");
        for (int i = 0; i < n; i++)
            System.out.print("C[" + i + "]=" + C[i] + "\t");
        System.out.println();

        System.out.println("Vrijeme preokreta>");
        for (int i = 0; i < n; i++)
            System.out.print("T[" + i + "]=" + T[i] + "\t");
        System.out.println();

        System.out.println("Vrijeme cekanja>");
        for (int i = 0; i < n; i++)
            System.out.print("W[" + i + "]=" + W[i] + "\t");
        System.out.println();

        float prosjek_T = 0;
        for (int i = 0; i < n; i++)
            prosjek_T += T[i];

        System.out.println("Prosjek za T:" + prosjek_T/n);

        float prosjek_W = 0;
        for (int i = 0; i < n; i++)
            prosjek_W += W[i];

        System.out.println("Prosjek za W:" + prosjek_W/n);

    }
}
