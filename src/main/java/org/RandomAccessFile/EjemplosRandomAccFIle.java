package org.RandomAccessFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class EjemplosRandomAccFIle {

        public static void main(String[] args) {

            // EJEMPLO DE CREACIÓN, ESCRITURA Y LECTURA EN UN RAF
            try {
                RandomAccessFile raf = new RandomAccessFile("proba.txt", "rw"); // modo lectura y escritura
                raf.writeUTF("Hola, mundo!"); // método para escribir  un String
                raf.seek(0);  // sitúa el puntero al principio
                System.out.println(raf.readUTF()); // lectura del archivo creado
                raf.close(); // es necesario cerrar el flujo
            } catch (IOException e) {
                e.printStackTrace();
        }
        }

}
