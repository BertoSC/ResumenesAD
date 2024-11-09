package org.RandomAccessFile;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Ejercicio2 {
       RandomAccessFile raf;
    {
                try {
                    raf = new RandomAccessFile("prueba2.txt", "rw");
                    for (int i=1; i<11; i++) {

                        raf.writeInt(i);
                    }
                    raf.seek(0);
                    for (int pos=0; pos<10; pos++) {
                        System.out.println(raf.readInt());
                    }
                    raf.close();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
    }


            public static void main(String[] args) {

                new org.RandomAccessFile.Ejercicio2();
            }


        }




