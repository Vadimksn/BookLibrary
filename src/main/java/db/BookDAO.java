package db;

import model.Book;
import model.Student;
import service.ConnectionService;
import utils.properties.PropertiesHolder;
import utils.converter.ResultSetConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BookDAO {

    public void save(Book book) {
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.INSERT_BOOK)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getEdition());
            preparedStatement.setString(4, book.getYearOfPublication());
            preparedStatement.execute();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void delete(Book book) {
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.DELETE_BOOK_BY_ID)) {
            connection.setAutoCommit(false);
            preparedStatement.setInt(1, book.getId());
            preparedStatement.execute();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Book book) {
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.UPDATE_BOOK_BY_ID)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getEdition());
            preparedStatement.setString(4, book.getYearOfPublication());
            preparedStatement.setInt(5, book.getId());
            preparedStatement.execute();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void give(Book book, Student student) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(PropertiesHolder.getProperty("DATE_FORMAT"));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 7);
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement1 = connection.prepareStatement(PreparedQuery.INSERT_INTO_STUDENT_BOOKS);
             PreparedStatement preparedStatement2 = connection.prepareStatement(PreparedQuery.SET_BOOK_AVAILABLE_BY_ID)) {
            connection.setAutoCommit(false);
            preparedStatement1.setInt(1, student.getId());
            preparedStatement1.setInt(2, book.getId());
            preparedStatement1.execute();
            preparedStatement2.setInt(1, 0);
            preparedStatement2.setString(2, dateFormat.format(new Date()));
            preparedStatement2.setString(3, dateFormat.format(calendar.getTime()));
            preparedStatement2.setInt(4, book.getId());
            preparedStatement2.execute();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void take(Book book) {
        book.setDateOfGive(null);
        book.setDateOfTake(null);
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement1 = connection.prepareStatement(PreparedQuery.SET_BOOK_AVAILABLE_BY_ID);
             PreparedStatement preparedStatement2 = connection.prepareStatement(PreparedQuery.DELETE_FROM_STUDENT_BOOKS_BY_ID)) {
            connection.setAutoCommit(false);
            preparedStatement1.setInt(1, 1);
            preparedStatement1.setString(2, "");
            preparedStatement1.setString(3, "");
            preparedStatement1.setInt(4, book.getId());
            preparedStatement1.execute();
            preparedStatement2.setInt(1, book.getId());
            preparedStatement2.execute();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Book getBookById(int id) {
        Book book = new Book();
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.SELECT_BOOK_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            book = ResultSetConverter.getBook(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    public Book getLastAddedBook() {
        Book book = new Book();
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.GET_LAST_ADDED_BOOK)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            book = ResultSetConverter.getBook(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    public List<Book> getListAllBooks() {
        List<Book> list = new ArrayList<>();
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.SELECT_ALL_BOOKS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(ResultSetConverter.getBook(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Book> getListAvailableBooks() {
        List<Book> list = new ArrayList<>();
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.SELECT_AVAILABLE_BOOKS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(ResultSetConverter.getBook(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Book> getListNotAvailableBooks() {
        List<Book> list = new ArrayList<>();
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.SELECT_NOT_AVAILABLE_BOOKS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(ResultSetConverter.getNotAvailableBook(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Book> getBookListByStudent(Student student) {
        List<Book> list = new ArrayList<>();
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.SELECT_BOOKS_BY_STUDENT)) {
            preparedStatement.setInt(1, student.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(ResultSetConverter.getBook(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
