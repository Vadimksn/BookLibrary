package db;

import model.Book;

public class test {

    public static void main(String[] args) {

        BookDAO bookDAO = new BookDAO();
        Book book = new Book("999", "999", "1113", "1222");
        book.setId(15);
        bookDAO.update(book);

    }
}
