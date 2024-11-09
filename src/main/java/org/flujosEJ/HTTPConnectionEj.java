package org.flujosEJ;
import java.io.*;
import java.net.*;
public class HTTPConnectionEj {


        public static void main(String[] args) {
            try {
                // Crear la URL del recurso que quieres acceder
                URL url = new URL("https://manuais.pages.iessanclemente.net/plantillas/dam/ad/");

                // Abrir la conexión y obtener una instancia de HttpURLConnection
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // Configurar la solicitud
                connection.setRequestMethod("GET");  // Establece el método HTTP (GET, POST, etc.)
                connection.setConnectTimeout(5000);  // Timeout para conectar
                connection.setReadTimeout(5000);     // Timeout para leer la respuesta

                // Leer la respuesta
                int responseCode = connection.getResponseCode();  // Obtener el código de respuesta HTTP
                System.out.println("Código de respuesta: " + responseCode);

                // Si la respuesta es exitosa (200 OK), leer el contenido
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = connection.getInputStream();  // Flujo de entrada de la respuesta
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream)); // Leer línea por línea
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);  // Imprimir cada línea de la respuesta
                    }
                    reader.close();  // Cerrar el lector
                } else {
                    System.out.println("Error en la conexión: " + responseCode);
                }

                connection.disconnect();  // Desconectar después de usar la conexión
            } catch (IOException e) {
                e.printStackTrace();  // Manejo de excepciones
            }
        }
    }


