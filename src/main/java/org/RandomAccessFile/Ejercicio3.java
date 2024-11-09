package org.RandomAccessFile;

import java.awt.desktop.SystemSleepEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio3 {

    RandomAccessFile raf;

    {
        try {
            raf = new RandomAccessFile("prueba3.bin", "rw");
            for (int i=1; i<11; i++) {
                raf.writeInt(i);
            }

            raf.seek(0);

            for (int i=1; i<11; i++) {
                System.out.println(raf.readInt());
            }

            raf.seek(2*4); // el puntero avanza byte a byte, y cada int ocupa 4 bytes

            Scanner sc = new Scanner(System.in);
            System.out.println("Introduce el número que sustituirá al 3");
            int nuevoN = sc.nextInt();

            raf.writeInt(nuevoN);

            raf.seek(0);

            for (int i=1; i<11; i++) {
                System.out.println(raf.readInt());
            }



        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {

        new Ejercicio3();
     }

}
