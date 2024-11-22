package org.BBDD;

import java.io.Serializable;
import java.util.Date;

public class Book implements Serializable {
    private Long idBook;
    private String isbn;
    private String title;
    private String author;
    private Integer year;
    private Boolean avaliable;
    private byte[] portada;
    private Date dataPublicacion;

    public Book(){}




}