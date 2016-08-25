/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joachim E. Christensen
 */
public class Threads {

    /**
     * @param args the command line arguments
     */
    static boolean stop = false;
    public static void main(String[] args) {
        Thread1 r = new Thread1();
        Thread2 s = new Thread2();
        Thread3 t = new Thread3();
        r.start();
        s.start();
        t.start();
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
}
