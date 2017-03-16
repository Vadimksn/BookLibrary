package db;

/**
 * Created by Vadim on 07.03.2017.
 */
public class PreparedQuery {

    static final String INSERT_STUDENT = "INSERT INTO Student (name,surname,middle_name,passport_data,registration_date," +
            "blacklist_date) VALUES (?,?,?,?,?,?)";
    static final String DELETE_STUDENT_BY_ID = "DELETE FROM Student WHERE id=?";
    static final String UPDATE_STUDENT_BY_ID = "UPDATE Student SET name=?,surname=?,middle_name=?,passport_data=? WHERE id=?";
    static final String SELECT_STUDENT_BY_ID = "SELECT * FROM Student WHERE id=?";
    static final String SELECT_STUDENTS = "SELECT * FROM Student WHERE blacklist=?";
    static final String ADD_STUDENT_TO_BLACKLIST_BY_ID = "UPDATE Student SET blacklist=1 WHERE id=?";
    static final String DELETE_STUDENT_FROM_BLACKLIST_BY_ID = "UPDATE Student SET blacklist=0 WHERE id=?";
    static final String SELECT_STUDENT_BOOKS = "SELECT * from Book b INNER JOIN Student_books sb on b.id=sb.book_id and sb.student_id=?";

    static final String INSERT_BOOK = "INSERT INTO Book(title,author,edition,year_of_publication,date_of_give,date_of_take)" +
            "VALUES (?,?,?,?,'','')";
    static final String DELETE_BOOK_BY_ID = "DELETE FROM Book WHERE id=?";
    static final String UPDATE_BOOK_BY_ID = "UPDATE Book SET title=?,author=?,edition=?,year_of_publication=? WHERE id=?";
    static final String DELETE_FROM_STUDENT_BOOKS_BY_ID = "DELETE FROM Student_books WHERE book_id=?";
    static final String SELECT_BOOK_BY_ID = "SELECT * FROM Book WHERE id=?";
    static final String SELECT_ALL_BOOKS = "SELECT * FROM Book";
    static final String SELECT_AVAILABLE_BOOKS = "SELECT * FROM Book WHERE available=1";
    static final String SELECT_NOT_AVAILABLE_BOOKS = "SELECT * FROM Book b INNER JOIN Student_books sb  on b.id=sb.book_id";
    static final String INSERT_INTO_STUDENT_BOOKS = "INSERT INTO Student_books(student_id,book_id) VALUES (?,?)";
    static final String SET_BOOK_AVAILABLE_BY_ID = "UPDATE Book SET available=?,date_of_give=?,date_of_take=? WHERE id=?";

}
