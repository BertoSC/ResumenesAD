package jsonGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Objects;

public class Imagen {
    String urlImg;
    byte [] img;

    public Imagen(String urlImg) {
        this.urlImg = urlImg;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public Imagen setUrlImg(String urlImg) {
        this.urlImg = urlImg;
        return this;
    }

    public byte[] getImg() {
        return img;
    }

    public Imagen setImg(byte[] img) {
        this.img = img;
        return this;
    }

    public byte [] generarBytes() throws URISyntaxException, IOException {
        BufferedImage img = ImageIO.read(new URI(this.urlImg).toURL());
        ByteArrayOutputStream in = new ByteArrayOutputStream();
        ImageIO.write(img, "jpg", in);
        this.img=in.toByteArray();
        return this.img;

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Imagen imagen = (Imagen) o;
        return Objects.equals(urlImg, imagen.urlImg) && Arrays.equals(img, imagen.img);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(urlImg);
        result = 31 * result + Arrays.hashCode(img);
        return result;
    }

    @Override
    public String toString() {
        return "Imagen{" +
                "urlImg='" + urlImg + '\'' +
                ", img=" + Arrays.toString(img) +
                '}';
    }
}
