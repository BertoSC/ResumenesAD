package org.flujosEJ;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FlujosImagen {
    public static void main(String[] args) {

        try {
            // GUARDAR UNA IMAGEN CON IMAGE IO -desde File y desde URL

            //BufferedImage img = ImageIO.read(new File("C:\\Users\\VSPC-BLACKFRIDAY\\Desktop\\recursosAPPCatStory\\adulto.png"));
            BufferedImage img = ImageIO.read(new URI("https://www.freetogame.com/g/306/thumbnail.jpg").toURL());

            String destino = "pruebaURL.png";

            ImageIO.write(img, "png", new File(destino));

            // GUARDAR UNA IMAGEN DESDE URL A LOCAL USANDO FLUJOS

            BufferedInputStream in = new BufferedInputStream(new URI("https://www.freetogame.com/g/306/thumbnail.jpg").toURL().openStream());

            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("pruebaSinIO.png"));
            byte [] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead=in.read(buffer, 0, 1024))!=-1){
                out.write(buffer,0, bytesRead);
            }
            in.close();
            out.close();

            // USANDO EL FLUJO IN ANTERIOR, LO MISMO PERO GUARDADA EN UN ARRAY DE BYTES

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            byte[] buffer2 = new byte[1024];
            int bytesReading;

            // Leer la imagen y almacenarla en el ByteArrayOutputStream
            while ((bytesReading = in.read(buffer2)) != -1) {
                byteArrayOutputStream.write(buffer2, 0, bytesReading);
            }

            // Convertir los datos leídos en un arreglo de bytes
            byte[] imageBytes = byteArrayOutputStream.toByteArray();

            // Cerrar los streams
            in.close();
            byteArrayOutputStream.close();

            // AHORA SE CONVIERTE EN UN OBJETO DE TIPO IMAGE DESDE EL ARRAY DE BITES

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageBytes);

            // Usar ImageIO para leer la imagen desde el ByteArrayInputStream y convertirla en un objeto Image
            Image image = ImageIO.read(byteArrayInputStream);





        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }
}
