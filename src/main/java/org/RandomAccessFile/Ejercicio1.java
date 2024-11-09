package org.RandomAccessFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Ejercicio1 {
    RandomAccessFile raf;
    {
        try {
            raf = new RandomAccessFile("prueba.txt", "rw");
            String frase = "Hola, este es un archivo para probar RAF, a ver qué tal va";
            raf.writeUTF(frase);
            raf.seek(0);
            System.out.println(raf.readUTF());
            raf.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new Ejercicio1();
    }


}
