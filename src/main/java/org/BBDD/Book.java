package org.BBDD;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getIdBook() {
        return idBook;
    }

    public void setIdBook(Long idBook) {
        this.idBook = idBook;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Boolean getAvaliable() {
        return avaliable;
    }

    public void setAvaliable(Boolean avaliable) {
        this.avaliable = avaliable;
    }

    public byte[] getPortada() {
        return portada;
    }

    public void setPortada(byte[] portada) {
        this.portada = portada;
    }

    public Date getDataPublicacion() {
        return dataPublicacion;
    }

    public void setDataPublicacion(Date dataPublicacion) {
        this.dataPublicacion = dataPublicacion;
    }

    //setPortada(File f): asigna una portada desde un archivo.

    public void setPortada(File f) {
      /*  File temp = f;
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


    }

    public void setPortada(String f) {
        File temp = new File(f);
        setPortada(temp);

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
                ((autor==null ||autor.isEm))



    }
}