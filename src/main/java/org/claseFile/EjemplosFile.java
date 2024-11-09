package org.claseFile;
import java.io.File;
import java.text.SimpleDateFormat;

public class EjemplosFile {
    public static void main(String[] args) {

       // ejemplo de listar las raíces de directorios del sistema
       // es un método estático

       System.out.println("EJERCICIO: listar las raíces del sistema");
       System.out.println();

       File [] roots = File.listRoots();
       for (File f:roots){
           System.out.println(f);
       }

       System.out.println();
       System.out.println("EJERCICIO: listar el contenido de un directorio y detallar los datos");
       System.out.println();

       // listar contenido de directorio (sin entrar en las subramas)

        var arquivo = new File("C:\\Users\\VSPC-BLACKFRIDAY\\Desktop\\AD\\Repaso_Java_IO_claseFile");
        System.out.println("La ruta "+arquivo.getAbsolutePath()+ " existe?: " + arquivo.exists());
        if (arquivo.exists()) {
            System.out.println("Ruta absoluta: " + arquivo.getAbsolutePath());
            System.out.println("Es un directorio: " + arquivo.isDirectory());
            System.out.println("Ruta padre: " + arquivo.getParent());
            System.out.println();

            if (arquivo.isFile()) {
                System.out.println("Tamaño: " + arquivo.length());
                System.out.println("Última modificación: " + arquivo.lastModified());

            } else {
                for (File subArquivo : arquivo.listFiles()) {
                    System.out.print(" " + subArquivo.getName());
                    System.out.println(subArquivo.isDirectory()?" >> Es un subdirectorio":" >> Es un archivo");
                    System.out.println(" Su tamaño es: "+subArquivo.length());
                    System.out.println(" Su ruta absoluta es: "+subArquivo.getAbsolutePath());
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  // aplicamos formato para la fecha
                    String fechaFormateada = sdf.format(subArquivo.lastModified()); // convertimos la cadena que devuelve el método
                    System.out.println(" Última modificación: "+ fechaFormateada);
                    System.out.println();

                }
            }
        }


    }
}