package org.ejemplosPathFiles;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EjemplosWalkyEjNIOExamen {
    public static void main(String[] args) {
        Path p = Paths.get("C:\\Users\\VSPC-BLACKFRIDAY\\Desktop");
      /*  try {
            Stream<Path> s = Files.walk(p, Integer.MAX_VALUE);
            s.filter(file-> file.toString().endsWith(".java"))
                    .forEach(System.out::println);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("EJEMPLO DE METODO LIST");

        try {
            Stream <Path> s= Files.list(p);
            s.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    */

        System.out.println("EJEMPLO DE MÉTODO");

        try{
            List<Path> listDir = listDirectories(p);
            for (Path ruta:listDir){
                System.out.println(ruta.getFileName());
            }
            System.out.println();
            List<Path> lista= findByFileExtension(p, ".java");
            for (Path ruta:lista){
                Path prueba = Paths.get("C:\\Users\\VSPC-BLACKFRIDAY\\Desktop\\prueba\\");
                Files.copy(ruta, prueba.resolve(ruta.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                System.out.println(ruta.getFileName());
            }
        } catch (Exception ex){
            System.out.println("ERROR");

        }

    }

    public static List<Path> findByFileExtension(Path pat, String ext){
        if (Files.isDirectory(pat)){
            try (Stream<Path> s = Files.walk(pat)){
                return s.filter(file->Files.isRegularFile(file))
                        .filter(file->file.getFileName().toString().endsWith(ext))
                 .collect(Collectors.toList());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
                throw new IllegalArgumentException();
        }
    }

    public static List<Path> listDirectories(Path pathDirectorio){
       if (Files.isDirectory(pathDirectorio)){
           try(Stream<Path> s = Files.list(pathDirectorio)){
               return s.filter(file->Files.isDirectory(file))
                       .collect(Collectors.toList());
           } catch (IOException e) {
               throw new RuntimeException(e);
           }

       } else {
           throw new IllegalArgumentException();
       }
    }

    public static void createFile(Path pathDirectorio, List<Path> archivos, String nombreArchivo) {
        try {
            Path archivo = Files.createFile(pathDirectorio.resolve(nombreArchivo));
            for (Path p : archivos) {
                try (BufferedReader br = new BufferedReader(new FileReader(p.toFile()));
                     BufferedWriter bw = new BufferedWriter(new FileWriter(archivo.toFile()))
                ){
                    String linea;
                    while ((linea=br.readLine())!=null){
                        bw.write(linea);
                    }


                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /*
    * public static void createFile(Path pathDirectorio, List<Path> archivos, String nombreArchivo) {
    // Validar que el directorio existe
    if (!Files.isDirectory(pathDirectorio)) {
        throw new IllegalArgumentException("La ruta indicada no es un directorio válido: " + pathDirectorio);
    }

    // Validar que la lista de archivos no es nula ni vacía
    if (archivos == null || archivos.isEmpty()) {
        throw new IllegalArgumentException("La lista de archivos no puede ser nula ni vacía.");
    }

    // Crear el archivo destino
    Path archivoSalida = pathDirectorio.resolve(nombreArchivo);
    if (Files.exists(archivoSalida)) {
        throw new IllegalArgumentException("El archivo ya existe: " + archivoSalida);
    }

    try {
        Files.createFile(archivoSalida); // Crear archivo vacío
    } catch (IOException e) {
        throw new RuntimeException("No se pudo crear el archivo: " + archivoSalida, e);
    }

    // Escribir el contenido de los archivos en el archivo de salida
    try (BufferedWriter writer = Files.newBufferedWriter(archivoSalida)) {
        for (Path archivo : archivos) {
            if (!Files.isRegularFile(archivo)) {
                System.out.println("Saltando, no es un archivo regular: " + archivo);
                continue; // Saltar si no es un archivo válido
            }

            try (BufferedReader reader = Files.newBufferedReader(archivo)) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    writer.write(linea);
                    writer.newLine(); // Agregar salto de línea entre líneas
                }
            } catch (IOException e) {
                System.err.println("Error al leer el archivo: " + archivo + " - " + e.getMessage());
            }
        }
    } catch (IOException e) {
        throw new RuntimeException("Error al escribir en el archivo de salida: " + archivoSalida, e);
    }
}
    *
    *
    *
    * */

}
