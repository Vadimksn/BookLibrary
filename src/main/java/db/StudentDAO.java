package db;

import model.Book;
import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadim on 27.02.2017.
 */
public class StudentDAO {

    private Connection con = null;
    private Statement stmt = null;
    private String url = "jdbc:sqlite:C:/Users/Vadim/Desktop/Приклади/LibraryDB.db";


    public void save(Student student) {
        try {
            con = DriverManager.getConnection(url);
            con.setAutoCommit(false);
            stmt = con.createStatement();
            String sql = String.format("INSERT INTO Student (name,surname,middle_name,passport_data,registration_date," +
                            "blacklist_date) VALUES ('%s','%s','%s','%s','%s','%s');",
                    student.getName(), student.getSurname(), student.getMiddleName(),
                    student.getPassportData(), student.getRegistrationDate(), student.getBlacklistDate());
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

    public void delete(Student student) {
        try {
            con = DriverManager.getConnection(url);
            con.setAutoCommit(false);
            stmt = con.createStatement();
            String sql = String.format("DELETE FROM Student WHERE id=%d;", student.getId());
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

    public void update(Student student) {
        try {
            con = DriverManager.getConnection(url);
            con.setAutoCommit(false);
            stmt = con.createStatement();
            String sql = String.format("UPDATE Student SET name='%s',surname='%s',middle_name='%s',passport_data='%s' WHERE id=%d;",
                    student.getName(), student.getSurname(), student.getMiddleName(), student.getPassportData(), student.getId());
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

    private Student getStudentFromResultSet(ResultSet resultSet) {
        Student student = new Student();
        try {
            student.setId(resultSet.getInt("id"));
            student.setName(resultSet.getString("name"));
            student.setSurname(resultSet.getString("surname"));
            student.setMiddleName(resultSet.getString("middle_name"));
            student.setPassportData(resultSet.getString("passport_data"));
            student.setRegistrationDate(resultSet.getString("registration_date"));
            if (resultSet.getInt("blacklist") == 1) {
                student.setBlacklist(true);
            } else student.setBlacklist(false);
            student.setBlacklistDate(resultSet.getString("blacklist_date"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;

    }

    public Student getStudentById(int id) {
        Student student = new Student();
        try {
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            String sql = String.format("SELECT * FROM Student WHERE id=%d;", id);
            ResultSet rs = stmt.executeQuery(sql);
            student = getStudentFromResultSet(rs);

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
        return student;
    }

    public List<Student> getListAllStudents() {
        List list = new ArrayList();
        Student student = new Student();
        try {
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            String sql = "SELECT * FROM Student";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                list.add(getStudentFromResultSet(rs));
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;


    }

    public void saveInBlackList(Student student) {
        try {

            con = DriverManager.getConnection(url);
            con.setAutoCommit(false);
            stmt = con.createStatement();
            String sql = String.format("UPDATE Student SET blacklist=%d WHERE id=%d;", 1, student.getId());
            stmt.executeUpdate(sql);
            con.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteFromBlackList(Student student) {
        try {
            con = DriverManager.getConnection(url);
            con.setAutoCommit(false);
            stmt = con.createStatement();
            String sql = String.format("UPDATE Student SET blacklist=%d WHERE id=%d;", 0, student.getId());
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

    public List<Book> getStudentBookList(Student student) {
        List list = new ArrayList();
        BookDAO bookDAO = new BookDAO();
        try {
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            String sql = String.format("SELECT * " +
                            "from Book b INNER JOIN spr_bookList spr on b.id=spr.book_id and spr.student_id=%d",
                    student.getId());
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                list.add(bookDAO.getBookFromResultSet(rs));
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
