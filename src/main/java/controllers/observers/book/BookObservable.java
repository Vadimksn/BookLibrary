package controllers.observers.book;

import model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookObservable  {

    private static List<BookObserver> bookObservers = new ArrayList<>();

    public static void registerBookObserver(BookObserver bookObserver){
        bookObservers.add(bookObserver);
    }

    public static void onBookAdded(Book book){
        for (BookObserver bookObserver : bookObservers) {
            bookObserver.onBookAdded(book);
        }
    }

    public static void onBookDeleted(Book book){
        for (BookObserver bookObserver : bookObservers) {
            bookObserver.onBookDeleted(book);
        }
    }

    public static void onBookEdit(Book book){
        for (BookObserver bookObserver : bookObservers) {
            bookObserver.onBookEdit(book);
        }
    }

    public static void onBookGiven(Book book){
        for (BookObserver bookObserver : bookObservers) {
            bookObserver.onBookGiven(book);
        }
    }

    public static void onBookTaken(Book book){
        for (BookObserver bookObserver : bookObservers) {
            bookObserver.onBookTaken(book);
        }
    }
}
