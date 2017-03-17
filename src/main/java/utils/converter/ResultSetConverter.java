package utils.converter;

import model.Book;
import model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetConverter {

    public static Student getStudent(ResultSet resultSet) throws SQLException {
        Student student = new Student();

        student.setId(resultSet.getInt("id"));
        student.setName(resultSet.getString("name"));
        student.setSurname(resultSet.getString("surname"));
        student.setMiddleName(resultSet.getString("middle_name"));
        student.setPassportData(resultSet.getString("passport_data"));
        student.setRegistrationDate(resultSet.getString("registration_date"));
        if (resultSet.getInt("blacklist") == 0) {
            student.setBlacklist(false);
        } else student.setBlacklist(true);
        student.setBlacklistDate(resultSet.getString("blacklist_date"));

        return student;
    }

    public static Book getBook(ResultSet resultSet) throws SQLException {
        Book book = new Book();

        book.setId(resultSet.getInt("id"));
        book.setTitle(resultSet.getString("title"));
        book.setAuthor(resultSet.getString("author"));
        book.setEdition(resultSet.getString("edition"));
        book.setYearOfPublication(resultSet.getString("year_of_publication"));
        book.setDateOfGive(resultSet.getString("date_of_give"));
        book.setDateOfTake(resultSet.getString("date_of_take"));
        if (resultSet.getInt("available") == 1) {
            book.setAvailable(true);
        } else book.setAvailable(false);

        return book;
    }

    public static Book getNotAvailableBook(ResultSet resultSet) throws SQLException {
        Book book = new Book();

        book.setId(resultSet.getInt("id"));
        book.setTitle(resultSet.getString("title"));
        book.setAuthor(resultSet.getString("author"));
        book.setEdition(resultSet.getString("edition"));
        book.setYearOfPublication(resultSet.getString("year_of_publication"));
        book.setDateOfGive(resultSet.getString("date_of_give"));
        book.setDateOfTake(resultSet.getString("date_of_take"));
        book.setStudentId(resultSet.getInt("student_id"));
        if (resultSet.getInt("available") == 1) {
            book.setAvailable(true);
        } else book.setAvailable(false);

        return book;
    }

}
