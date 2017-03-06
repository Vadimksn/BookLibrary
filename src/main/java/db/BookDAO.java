package db;

import model.Book;
import model.Student;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Vadim on 28.02.2017.
 */
public class BookDAO {

    private Connection con = null;
    private Statement stmt = null;
    private String url = "jdbc:sqlite:C:/Users/Vadim/Desktop/Приклади/LibraryDB.db";

    public void save(Book book) {
        try {
            con = DriverManager.getConnection(url);
            con.setAutoCommit(false);
            stmt = con.createStatement();
            String sql = String.format("INSERT INTO  Book(title,author,edition,year_of_publication,date_of_give," +
                            "date_of_take) VALUES ('%s','%s','%s','%s','','')",
                    book.getTitle(), book.getAuthor(), book.getEdition(), book.getYearOfPublication());
            stmt.executeUpdate(sql);
            con.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    public void delete(Book book) {
        try {
            con = DriverManager.getConnection(url);
            con.setAutoCommit(false);
            stmt = con.createStatement();
            String sql = String.format("DELETE FROM Book WHERE id=%d;", book.getId());
            stmt.executeUpdate(sql);
            con.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    public void update(Book book) {
        try {
            con = DriverManager.getConnection(url);
            con.setAutoCommit(false);
            stmt = con.createStatement();
            String sql = String.format("UPDATE Book SET title='%s',author='%s',edition='%s',year_of_publication='%s' WHERE id=%d;",
                    book.getTitle(), book.getAuthor(), book.getEdition(), book.getYearOfPublication(), book.getId());
            stmt.executeUpdate(sql);
            con.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    public void give(Book book, Student student) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 7);
        try {

            con = DriverManager.getConnection(url);
            con.setAutoCommit(false);
            stmt = con.createStatement();
            String sql = String.format("INSERT INTO spr_bookList(student_id,book_id) VALUES (%d,%d)",
                    student.getId(), book.getId());
            stmt.executeUpdate(sql);
            sql = String.format("UPDATE Book SET date_of_give='%s',date_of_take='%s' WHERE id=%d;",
                    dateFormat.format(date), dateFormat.format(calendar.getTime()), book.getId());
            stmt.executeUpdate(sql);
            con.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }


    }

    public void take(Book book) {
        book.setDateOfGive(null);
        book.setDateOfTake(null);
        try {
            con = DriverManager.getConnection(url);
            con.setAutoCommit(false);
            stmt = con.createStatement();
            String sql = String.format("UPDATE Book SET date_of_give='',date_of_take='' WHERE id=%d;",
                    book.getId());
            stmt.executeUpdate(sql);
            sql = String.format("DELETE FROM spr_bookList WHERE book_id=%d", book.getId());
            stmt.executeUpdate(sql);
            con.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    public Book getBookFromResultSet(ResultSet resultSet) {
        Book book = new Book();

        try {
            book.setId(resultSet.getInt("id"));
            book.setTitle(resultSet.getString("title"));
            book.setAuthor(resultSet.getString("author"));
            book.setEdition(resultSet.getString("edition"));
            book.setYearOfPublication(resultSet.getString("year_of_publication"));
            book.setDateOfGive(resultSet.getString("date_of_give"));
            book.setDateOfTake(resultSet.getString("date_of_take"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return book;
    }

    public Book getBookById(int id) {
        Book book = new Book();
        try {
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            String sql = String.format("SELECT * FROM Book WHERE id=%d;", id);
            ResultSet rs = stmt.executeQuery(sql);
            book = getBookFromResultSet(rs);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return book;
    }

    public List<Book> getListAllBooks() {
        List list = new ArrayList();
        Book book = new Book();
        try {
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            String sql = "SELECT * FROM Book";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                list.add(getBookFromResultSet(rs));
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return list;
    }
}
