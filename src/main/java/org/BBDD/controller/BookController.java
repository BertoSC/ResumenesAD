package org.BBDD.controller;
import org.BBDD.Book;
import org.BBDD.DAO;
import org.BBDD.vista.IBookView;

public class BookController implements IBookController{
    @Override
    public boolean createBook(String title, String author, String isbn, int year, boolean available, byte[] cover) {
        return false;
    }

    @Override
    public void getBook(int id) {

    }

    @Override
    public void updateBook(String title, String author, String isbn, int year, boolean available, byte[] cover) {

    }

    @Override
    public boolean deleteBook(int id) {
        return false;
    }

    @Override
    public boolean isLastBook(int id) {
        return false;
    }

    @Override
    public boolean isFirstBook(int id) {
        return false;
    }

    @Override
    public int getNextId(int id) {
        return 0;
    }

    @Override
    public int getPreviousId(int id) {
        return 0;
    }

    @Override
    public int getFirstId() {
        return 0;
    }

    @Override
    public void setView(IBookView view) {

    }

    @Override
    public void setDao(DAO<Book> dao) {

    }
}
