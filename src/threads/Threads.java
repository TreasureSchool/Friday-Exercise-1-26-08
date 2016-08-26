package threads;

import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * @author Joachim E. Christensen
 */
public class Threads {

    static boolean stop = false;
    static Even even = new Even();
    public static void main(String[] args) {
        //Opgave 1
        Thread1 r = new Thread1();
        Thread2 s = new Thread2();
        Thread3 t = new Thread3();
        r.start();
        s.start();
        t.start();
        
        //Opgave 2
        Thread4 u = new Thread4();
        Thread4 u2 = new Thread4();
        u.start();
        u2.start();
        /*Problemet der opstår hvis man kalder den samme metode flere gange på samme tid,
        er at der er stor chance for at man mister overblikket over hvornår hver af dem tager fat i den kaldte funktion.
        Dette kaldes for 'race condition', fordi at de flere Threads racer for at få adgang.
        Dette fikses ved at 'låse' funktionen så Threadsne bliver nød til at vente på hinanden.
        Dette har jeg opnået ved at ændre min Even funktion til at have en Synkroniserings faktor.
        Hvis man ikke holder styr på de metoder man bruger, vil man støde på 'race condition' ofte.*/ 
    }
    static class Thread1 extends Thread{
        public void run(){
            long sum = 0;
            for(long i = 1; i <= 1000000000; i++) {
                sum = sum+i;
            }
            System.out.println(sum);
            try {
                Thread1.sleep(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Threads.class.getName()).log(Level.SEVERE, null, ex);
            }
            stop = true;
        }
    }
    static class Thread2 extends Thread {
        public void run() {
            for (int i = 0; i <= 5; i++) {
                System.out.println(i);
                try {
                    Thread2.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Threads.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    static class Thread3 extends Thread {
        public void run() {
            int i = 10;
            while (i >= 10 && stop == false) {
                System.out.println(i);
                i++;
                try {
                    Thread3.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Threads.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    static class Thread4 extends Thread {
        public void run() {
            int counter = 0;
            for (int i = 1; i <= 1000000; i++){
                int e = even.next();
                if (e % 2 != 0) {
                    counter++;
                }
            }
            System.out.println(counter);
        }
    }
}
