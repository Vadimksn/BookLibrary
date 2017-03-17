package service.book;

import db.BookDAO;
import model.Book;
import model.Student;

import java.util.List;

public class BookService implements IBookService {
    private BookDAO bookDAO = new BookDAO();

    @Override
    public void saveBook(Book book) {
        bookDAO.save(book);
    }

    @Override
    public void deleteBook(Book book) {
        bookDAO.delete(book);
    }

    @Override
    public void updateBook(Book book) {
        bookDAO.update(book);
    }

    @Override
    public void giveBook(Book book, Student student) {
        bookDAO.give(book, student);
    }

    @Override
    public void takeBook(Book book) {
        bookDAO.take(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDAO.getListAllBooks();
    }

    @Override
    public List<Book> getAvailableBooks() {
        return bookDAO.getListAvailableBooks();
    }

    @Override
    public List<Book> getNotAvailableBooks() {
        return bookDAO.getListNotAvailableBooks();
    }

    @Override
    public Book getBookById(int id) {
        return bookDAO.getBookById(id);
    }
}
