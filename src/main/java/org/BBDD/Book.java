package org.BBDD;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Objects;

public class Book implements Serializable {
    private Long idBook;
    private String isbn;
    private String title;
    private String author;
    private int year;
    private Boolean avaliable;
    private byte[] portada;
    private Date dataPublicacion;

    public Book(){}

    public Book(String isbn, String title, String author, int year, Boolean avaliable){
        this.isbn=isbn;
        this.title=title;
        this.author=author;
        this.year=year;
        this.avaliable=avaliable;
    }

    public Book(Long idBook, String isbn, String title, String author, int year, Boolean avaliable, byte [] portada){
        this.idBook=idBook;
        this.isbn=isbn;
        this.title=title;
        this.author=author;
        this.year=year;
        this.avaliable=avaliable;
        this.portada=portada;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    public Long getIdBook() {
        return idBook;
    }

    public Book setIdBook(Long idBook) {
        this.idBook = idBook;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Book setAuthor(String author) {
        this.author = author;
        return this;
    }

    public int getYear() {
        return year;
    }

    public Book setYear(int year) {
        this.year = year;
        return this;
    }

    public Boolean getAvaliable() {
        return avaliable;
    }

    public Book setAvaliable(Boolean avaliable) {
        this.avaliable = avaliable;
        return this;
    }

    public byte[] getPortada() {
        return portada;
    }

    public Book setPortada(byte[] portada) {
        this.portada = portada;
        return this;
    }

    public Date getDataPublicacion() {
        return dataPublicacion;
    }

    public Book setDataPublicacion(Date dataPublicacion) {
        this.dataPublicacion = dataPublicacion;
        return this;
    }

    //setPortada(File f): asigna una portada desde un archivo.

    public Book setPortada(File f) {
        /*File temp = f;
        try (var in = new FileInputStream(temp)){
           byte [] img = new byte[(int)f.length()];
           int readed;
           int indice=0;
           while ((readed=in.read())!=-1){
               img[indice]=(byte)readed;
               indice++;
           }
           this.portada=img;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

        if(f==null|| !f.exists()){
            return this;
        }
        Path p = Paths.get(f.getAbsolutePath());
        // ByteArray flujo  almacena los datos en memoria como un array de bytes
        try(BufferedInputStream is = new BufferedInputStream(Files.newInputStream(p));
            ByteArrayOutputStream os = new ByteArrayOutputStream()){
            // buffer que almacenará temporalmente los datos leidos
            byte[] buffer = new byte[1024];
            // escribe los datos almacenados en el buffer, desde inicio hasta el valor de bytes leidos
            int bytesleidos;
            while ((bytesleidos = is.read())>0){
                os.write(buffer, 0, bytesleidos);
            }

            portada=os.toByteArray();
            return this;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Book setPortada(String f) {
        File temp = new File(f);
        setPortada(temp);
        return this;

    }

    public Image getImage(){
        try(ByteArrayInputStream flujo = new ByteArrayInputStream(portada)) {
            try {
                Image img= ImageIO.read(flujo);
                return img;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(isbn);
    }

    @Override
    public String toString() {
        return ((title==null ||title.isEmpty())? "*" : title) +
                ((author==null ||author.isEmpty())? "*" : author) +
                ((year == 0) ? "*" : year);

    }
}