package org.flujosEJ;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public class InfoCabecera {
    public static void main(String[] args) throws URISyntaxException, IOException {
        HttpURLConnection conexion = (HttpURLConnection) new URI(
                "https://manuais.pages.iessanclemente.net/plantillas/dam/ad/").toURL().openConnection();

        // Mostrar las cabeceras HTTP
        mostrarCabecerasHTTP(conexion);

        // Obtener el InputStream de la conexión
        try (InputStream is = conexion.getInputStream();
             BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream("copia." + getExtension(conexion)))) {

            int b;
            while ((b = is.read()) != -1) {
                fos.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Mostrar las cabeceras HTTP
    private static void mostrarCabecerasHTTP(HttpURLConnection conexion) {
        // Mostrar el código de respuesta HTTP
        try {
            System.out.println("Código de respuesta: " + conexion.getResponseCode());
            System.out.println("Mensaje de respuesta: " + conexion.getResponseMessage());

            // Obtener todas las cabeceras HTTP
            Map<String, java.util.List<String>> cabeceras = conexion.getHeaderFields();

            // Mostrar cada cabecera
            System.out.println("\nCabeceras HTTP:");
            for (Map.Entry<String, java.util.List<String>> entry : cabeceras.entrySet()) {
                String nombreCabecera = entry.getKey();
                java.util.List<String> valores = entry.getValue();
                System.out.println(nombreCabecera + ": " + String.join(", ", valores));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Obtener la extensión del archivo
    private static String getExtension(HttpURLConnection conexion) {
        // Obtener el tipo de contenido de la respuesta
        String contentType = conexion.getContentType();

        // Extraer la extensión del tipo de contenido
        return contentType.split("/")[1].split(";")[0];
    }
}
