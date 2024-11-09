package org.flujosEJ;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JFileChooser;

public class ejURL {


        public static void main(String[] args) throws IOException, URISyntaxException {

                    HttpURLConnection conexion = (HttpURLConnection) new URI(
                            "https://manuais.pages.iessanclemente.net/plantillas/dam/ad/").toURL().openConnection();
                    InputStream is = conexion.getInputStream();
                    String extension = conexion.getContentType().split("/")[1];
                    System.out.println(extension);
                    /*Utilizas getContentType() para obtener el tipo de contenido de la respuesta HTTP.
                     El tipo de contenido se devuelve en el formato tipo/subtipo, como image/png o application/pdf.
                      Luego, divides el tipo para obtener la extensión del archivo
                       (el subtipo, por ejemplo, png de image/png).*/

                    var fos = new BufferedOutputStream(new FileOutputStream("copia." + extension.split(";")[0]));
                    // se obtiene el html porque el String es html; charset=utf-8, al dividirlo por ; y quedarnos con el primer valor

                    int b;
                    while ((b = is.read()) != -1) {
                        System.out.print((char) b);
                        fos.write(b);
                    }

                }
            }


