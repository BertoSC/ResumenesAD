package org.claseFile;

/*
* Programa que ofrecerá diferentes funcionalidades para crear, listar, borrar o mover archivos/directorios
*
* Falta control de nulos en varias secciones del código, necesario para evitar problemas
* */

import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.io.File;
import java.util.Scanner;

public class GestorArchivos {
    Boolean funcionando = true;

    public GestorArchivos(){
        menu();
    }

    public void menu(){
        Scanner sc = new Scanner(System.in);
        while (funcionando){
            System.out.println("GESTOR DE ARCHIVOS");
            System.out.println(" 1. Crear directorio");
            System.out.println(" 2. Listar directorio");
            System.out.println(" 3. Eliminar archivo/directorio");
            System.out.println(" 4. Mover/renombrar archivo o directorio");
            System.out.println(" 5. Salir");
            System.out.println();
            System.out.println("Selecciona una opción: ");

            int opcion = sc.nextInt();

            if (opcion > 0 && opcion < 6){
                aplicarAccion(opcion);
                System.out.println();
                    
            } else {
                System.out.println("Ingrese una opción válida");
                System.out.println();                
            }
        }
    }

    private void aplicarAccion(int opcion) {
        switch (opcion){
            case 1:
                crearDirectorio();
                break;
            case 2:
                listarDirectorio();
                break;
            case 3:
                eliminarArDir();
                break;
            case 4:
                renombrarMover();
                break;
            case 5:
                System.out.println("Saliendo del programa...");
                System.out.println("Gracias por usar la aplicación");
                funcionando = false;
        }
    }

    private void renombrarMover() {   // abre ventana para seleccionar archivo o directorio
        JFileChooser fc = elegirArchivooDirectorio();
        int seleccion = fc.showOpenDialog(null);  // almacenamos la selección
        if (seleccion == JFileChooser.APPROVE_OPTION){  // si es ok, entra en el segmento
            File selected = fc.getSelectedFile();   // recuperamos la selecc y la almacenamos en un File
            int nuevaRuta = fc.showSaveDialog(null);  // abrimos ventana para guardar
                if (nuevaRuta == JFileChooser.APPROVE_OPTION) {
                    File nuevoF = fc.getSelectedFile();   // recuperamos la nueva selección y la almacenamos en File
                    if (selected.renameTo(nuevoF)){    // aplicamos la operación de mover (del primer File al nuevo)
                        System.out.println("Operación realizada con éxito");
                    } else {
                        System.out.println("Error en la operación: ya existe ese nombre");
                    }
                }
        }
    }

    private void eliminarArDir() {  // funciona de modo similar al de listar el directorio en profundidad, pero con delete()
        JFileChooser fc = elegirArchivooDirectorio();
        int seleccion = fc.showOpenDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION){
            File seleccionada = fc.getSelectedFile();
                if (seleccionada.exists() && seleccionada.isDirectory()){
                    eliminar(seleccionada);
                } else {
                    if (seleccionada.delete()){
                        System.out.println("Archivo eliminado");
                    }
                }
        }
    }

    private void eliminar(File f) {
        File[] subdirectorio = f.listFiles();
        for (File file : subdirectorio) {
            if (file.exists() && file.isFile()) {
                if (file.delete()) {
                    System.out.println("Archivo eliminado");
                } else {
                    System.out.println("Error");
                }
            } else {
                eliminar(file);
            }
        }
        if (f.exists() && f.delete()){  // para eliminar las carpetas, de lo contrario solo elimina archivos
            System.out.println("Directorio eliminado");
        } else {
            System.out.println("Error");
        }
    }

    private void listarDirectorio() {
        JFileChooser fc = elegirDirectorio();
        int seleccion = fc.showOpenDialog(null);
            if (seleccion == JFileChooser.APPROVE_OPTION){
                File directorioEscogido = fc.getSelectedFile();
                File [] listado = directorioEscogido.listFiles();  // listamos los archivos del directorio en un File
                System.out.println("Contenido del directorio: "+directorioEscogido.getAbsolutePath());
                    for (File f: listado){
                        if (f.isDirectory()){  // si es subdirectorio, lo listamos de nuevo con un submétodo recursivo
                            System.out.println(" Nombre del subdirectorio: "+f.getName());
                            listar(f, " ");
                        } else {  // si es archivo, imprimimos la información
                            System.out.print(" Nombre del archivo: " +f.getName());
                            System.out.println(" , Tamaño: "+f.length());
                        }
                    }

            }
    }

    private void listar(File f, String esp){  // método recursivo para listar subdirectorios
        File [] subdirectorio = f.listFiles();
        for (File file: subdirectorio){  // recorremos de nuevo todo el contenido del subdirectorio tras listarlo
            if (file.isFile()){   // si es un archivo, se imprime la info
                System.out.print(esp+"Nombre del archivo: " +file.getName());
                System.out.println(" , Tamaño: "+f.length());
            } else {   // si es otro directorio, se imprime el nombre y la indentación y se llama de nuevo a listar de forma recursiva
                System.out.println(esp+"Nombre del subdirectorio: "+file.getName());
                listar(file, esp+" ");
            }
        }
    }

    public void crearDirectorio(){
        JFileChooser fc = elegirDirectorio();  // limitamos la selección a directorios
        int seleccion= fc.showSaveDialog(null); // abrimos ventana dialogo para guardar/crear, que devuelve un entero
            if (seleccion==JFileChooser.APPROVE_OPTION){ // con ese entero se ve si se dio al botón de ok en la ventana
                File directorioNuevo= fc.getSelectedFile(); // se recupera la referencia de la ruta nueva
                if (directorioNuevo.mkdirs()) {   // creamos el directorio, si devuelve true, imprimimos la ruta absoluta
                    System.out.println("Se ha creado el directorio en la ruta: " + directorioNuevo.getAbsolutePath());
                } else {
                    System.out.println("Error en la operación: el directorio ya existe o se produjo un error");
                }
            } else {
                System.out.println("Operación cancelada"); // si no se da al ok, avisamos por consola de que se canceló la operación
            }

    }

    public JFileChooser elegirDirectorio(){
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        return fc;
    }

    public JFileChooser elegirArchivooDirectorio(){
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        return fc;
    }

    public static void main(String[] args) {
        new GestorArchivos();

    }
}
