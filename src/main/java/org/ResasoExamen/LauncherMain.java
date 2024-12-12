package org.ResasoExamen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LauncherMain {
    public static void main(String[] args) throws URISyntaxException, IOException, ParseException {
        /*String urlImg = "C:/Users/VSPC-BLACKFRIDAY/Desktop/recursosAPPCatStory/adulto.png";
        BufferedImage img = ImageIO.read(new File(urlImg));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(img, "png", out);
        byte[] imgBytes= out.toByteArray();

        ByteArrayInputStream in = new ByteArrayInputStream(imgBytes);
        BufferedImage imgdeVuelta= ImageIO.read(in);

        ImageIO.write(imgdeVuelta,"png", new File("perros.jpg"));

        LocalDate fecha = LocalDate.of(1985,10,24);
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateGson())
                        .setPrettyPrinting().create();

        String jsonFecha= gson.toJson(fecha);


        System.out.println(fecha);*/

        // Crear instancia de Gatito
        Gatito gatito = new Gatito( "Miau", "Siames", "C:/Users/VSPC-BLACKFRIDAY/Desktop/recursosAPPCatStory/adulto.png", LocalDate.of(2023, 12, 12));
        gatito.setFoto(gatito.conseguirArrayBites("C:/Users/VSPC-BLACKFRIDAY/Desktop/recursosAPPCatStory/adulto.png"));
        System.out.println(gatito);
        Type lista = new TypeToken<List<Gatito>>(){}.getType();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Gatito.class, new GatitoGson())
                .registerTypeAdapter(lista, new GatitoListaDes())
                .setPrettyPrinting()
                .create();

        try {
            MetodosBD bd = new MetodosBD();
            //bd.save(gatito);
            //System.out.println(gatito);


            List<Gatito> listaGat = bd.getAll();

            try (FileWriter writer = new FileWriter("gatitos.json")) {
                // Escribir todo el JSON en el archivo
                gson.toJson(listaGat, writer);  // Serializa la lista de Gatitos a JSON y la escribe en el archivo
                System.out.println("Los Gatitos se han guardado en el archivo gatitos.json.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }



        List<Gatito> listaG = gson.fromJson(Files.readString(Paths.get("gatitos.json")), lista);

        for (Gatito g: listaG){
            System.out.println(g);
        }




/*
        // Configurar Gson con el adaptador


        // Serializar

        System.out.println("JSON serializado:\n" + json);

        String s=("1985-12-12");

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha2 = formato.parse(s);
        System.out.println(fecha2);*/

    }
}
