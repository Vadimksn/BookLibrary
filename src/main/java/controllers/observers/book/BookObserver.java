package controllers.observers.book;

import model.Book;

public interface BookObserver {
    void onBookAdded(Book book);
    void onBookDeleted(Book book);
    void onBookEdit(Book book);
    void onBookGiven(Book book);
    void onBookTaken(Book book);
}
