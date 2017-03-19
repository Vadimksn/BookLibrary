package controllers.callbacks.book;

import model.Book;

public interface BookCallback {
    void onBookAdded(Book book);
    void onBookDeleted(Book book);
    void onBookEdit(Book book);
    void onBookGiven(Book book);
    void onBookTaken(Book book);
}
