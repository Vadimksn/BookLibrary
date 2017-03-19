package db;

import model.Book;

public class test {

    public static void main(String[] args) {

        BookDAO bookDAO = new BookDAO();
        Book book = new Book();
        book.setId(1);
        book = bookDAO.getBookById(book.getId());
        System.out.println(book.getTitle());

    }
}
