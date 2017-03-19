package controllers.callbacks.book;

import model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookObservable  {

    private static List<BookCallback> bookCallbacks = new ArrayList<>();

    public static void registerBookCallback(BookCallback callback){
        bookCallbacks.add(callback);
    }

    public static void onBookAdded(Book book){
        for (BookCallback bookCallback : bookCallbacks) {
            bookCallback.onBookAdded(book);
        }
    }

    public static void onBookDeleted(Book book){
        for (BookCallback bookCallback : bookCallbacks) {
            bookCallback.onBookDeleted(book);
        }
    }

    public static void onBookEdit(Book book){
        for (BookCallback bookCallback : bookCallbacks) {
            bookCallback.onBookEdit(book);
        }
    }

    public static void onBookGiven(Book book){
        for (BookCallback bookCallback : bookCallbacks) {
            bookCallback.onBookGiven(book);
        }
    }

    public static void onBookTaken(Book book){
        for (BookCallback bookCallback : bookCallbacks) {
            bookCallback.onBookTaken(book);
        }
    }
}
