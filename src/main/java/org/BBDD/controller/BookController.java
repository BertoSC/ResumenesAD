package org.BBDD.controller;
import org.BBDD.Book;
import org.BBDD.BookDAO;
import org.BBDD.DAO;
import org.BBDD.vista.BookView;
import org.BBDD.vista.IBookView;

import java.util.List;

public class BookController implements IBookController{
    DAO <Book> bookDAO;
    IBookView bookView;

    public BookController (DAO<Book> dao){
        this.bookDAO=dao;
    }

    @Override
    public boolean createBook(String title, String author, String isbn, int year, boolean available, byte[] cover) {
        return false;
    }

    @Override
    public void getBook(int id) {
        Book actual = bookDAO.get(id);
        bookView.setBookTitle(actual.getTitle());
        bookView.setAuthor(actual.getAuthor());
        bookView.setISBN(actual.getIsbn());
        bookView.setYear(actual.getYear());
        bookView.setAvailable(actual.getAvaliable());
        bookView.setCover(actual.getPortada());
        bookView.setID(actual.getIdBook());

    }

    @Override
    public void updateBook(String title, String author, String isbn, int year, boolean available, byte[] cover) {

    }

    @Override
    public boolean deleteBook(int id) {
        return false;
    }

    @Override
    public boolean isFirstBook(int id) {
        List<Integer> listaId = bookDAO.getAllIds();
        return listaId.indexOf(id) == 0;
    }

    @Override
    public boolean isLastBook(int id) {
        List<Integer> listaId = bookDAO.getAllIds();
        return listaId.indexOf(id) == listaId.size() - 1;
    }

    @Override
    public int getNextId(int id) {
        List<Integer> listaId = bookDAO.getAllIds();
        int index = listaId.indexOf(id);
        if (index != -1 && index < listaId.size() - 1) {
            return listaId.get(index + 1);
        }
        return id;
    }

    @Override
    public int getPreviousId(int id) {
        List<Integer> listaId = bookDAO.getAllIds();
        int index = listaId.indexOf(id);
        if (index > 0) {
            return listaId.get(index - 1);
        }
        return id;
    }

    @Override
    public int getFirstId() {
        List<Integer> listaId = bookDAO.getAllIds();
        if (!listaId.isEmpty()){
            return listaId.get(0);
        }
        return 0;
    }

    @Override
    public void setView(IBookView view) {
        this.bookView=view;

    }

    @Override
    public void setDao(DAO<Book> dao) {
        this.bookDAO=dao;

    }
}
