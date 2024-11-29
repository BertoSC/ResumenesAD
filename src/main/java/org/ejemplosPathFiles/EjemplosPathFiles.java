package org.ejemplosPathFiles;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class EjemplosPathFiles {
    public static void main(String[] args) {
        Path destino = Paths.get("C:/Users/VSPC-BLACKFRIDAY/Desktop/AD/CodigosPostales");
        Path archivo = Paths.get("C:/Users/VSPC-BLACKFRIDAY/Desktop/ej3.txt");

        // EJEMPLO DEL MÉTODO WALK
        // devuelve un stream de path
        try (Stream<Path> stream = Files.walk(destino, Integer.MAX_VALUE)) {
            stream.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println();

        // EJEMPLO DEL MÉTODO LIST
        // devuelve un Stream de Path

        try (Stream<Path> strm = Files.list(destino)) {
            strm.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // EJEMPLO DEL MÉTODO LINES
        // devuelve un stream de String

        try(Stream<String> lines = Files.lines(archivo)){
            lines.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // EJEMPLO DE FIND

        // se le pasa la ruta donde se quiere buscar y se le indica la profundidad
        // el tercer parámetro es un predicado,
        // en este caos, le dindicamos que queremos aquellos que se sean archivos y terminen en .Java y que los imprima

        try (Stream<Path> s = Files.find(destino, Integer.MAX_VALUE,
                (path, atributos) -> path.toString().endsWith(".java") && atributos.isRegularFile())){
            s.forEach(System.out::println);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println();

        // EJEMPLO DE READ ALL LINES
        // lee el contenido de un archivo y devuelve una lista de String

        try {
            List<String> s = Files.readAllLines(archivo);
            s.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println();

        // EJEMPLO DE READSTRING (lee archivo y devuelve una cadena)

        String s= null;
        try {
            s = Files.readString(archivo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(s);


    }

}