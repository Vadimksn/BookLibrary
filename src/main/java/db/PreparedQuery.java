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
    static final String SELECT_ALL_STUDENTS = "SELECT * FROM Student";
    static final String ADD_STUDENT_TO_BLACKLIST_BY_ID = "UPDATE Student SET blacklist=1 WHERE id=?";
    static final String DELETE_STUDENT_FROM_BLACKLIST_BY_ID = "UPDATE Student SET blacklist=0 WHERE id=?";
    static final String SELECT_STUDENT_BOOKS = "SELECT * from Book b INNER JOIN spr_bookList spr on b.id=spr.book_id and spr.student_id=?";

    static final String INSERT_BOOK = "INSERT INTO Book(title,author,edition,year_of_publication,date_of_give,date_of_take)" +
            "VALUES (?,?,?,?,'','')";
    static final String DELETE_BOOK_BY_ID = "DELETE FROM Book WHERE id=?";
    static final String UPDATE_BOOK_BY_ID = "UPDATE Book SET title=?,author=?,edition=?,year_of_publication=? WHERE id=?";
    static final String DELETE_DATE_OF_TAKEN_BOOK_BY_ID = "UPDATE Book SET date_of_give='',date_of_take='' WHERE id=?";
    static final String DELETE_FROM_SPRBOOKS_BY_ID = "DELETE FROM spr_bookList WHERE book_id=?";
    static final String SELECT_BOOK_BY_ID = "SELECT * FROM Book WHERE id=?";
    static final String SELECT_ALL_BOOKS = "SELECT * FROM Book";
    static final String INSERT_TO_SPRBOOKS = "INSERT INTO spr_bookList(student_id,book_id) VALUES (?,?)";
    static final String SET_DATE_OF_GIVEN_BOOK = "UPDATE Book SET date_of_give=?,date_of_take=? WHERE id=?";
}
