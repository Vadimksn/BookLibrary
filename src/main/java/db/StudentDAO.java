package db;

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
import java.util.Date;
import java.util.List;

public class StudentDAO {

    public void save(Student student) {
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.INSERT_STUDENT)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getMiddleName());
            preparedStatement.setString(4, student.getPassportData());
            preparedStatement.setString(5, student.getRegistrationDate());
            preparedStatement.setString(6, student.getBlacklistDate());
            preparedStatement.execute();
            connection.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void delete(Student student) {
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.DELETE_STUDENT_BY_ID)) {
            connection.setAutoCommit(false);
            preparedStatement.setInt(1, student.getId());
            preparedStatement.execute();
            connection.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void update(Student student) {
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.UPDATE_STUDENT_BY_ID)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getMiddleName());
            preparedStatement.setString(4, student.getPassportData());
            preparedStatement.setInt(5, student.getId());
            preparedStatement.execute();
            connection.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }

    }


    public Student getStudentById(int id) {
        Student student = new Student();

        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.SELECT_STUDENT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            student = ResultSetConverter.getStudent(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }
    public Student getLastAddedStudent() {
        Student student = new Student();

        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.GET_LAST_ADDED_STUDENT)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            student = ResultSetConverter.getStudent(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public List<Student> getListAllStudents() {
        List<Student> list = new ArrayList();

        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.SELECT_STUDENTS)) {
            preparedStatement.setInt(1, 0);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(ResultSetConverter.getStudent(resultSet));
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    public List<Student> getStudentsBlacklist() {
        List<Student> list = new ArrayList();

        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.SELECT_STUDENTS)) {
            preparedStatement.setInt(1, 1);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(ResultSetConverter.getStudent(resultSet));
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    public void addToBlackList(Student student) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(PropertiesHolder.getProperty("DATE_FORMAT"));

        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.ADD_STUDENT_TO_BLACKLIST_BY_ID)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, dateFormat.format(new Date()));
            preparedStatement.setInt(2, student.getId());
            preparedStatement.execute();
            connection.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteFromBlackList(Student student) {
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.DELETE_STUDENT_FROM_BLACKLIST_BY_ID)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, "");
            preparedStatement.setInt(2, student.getId());
            preparedStatement.execute();
            connection.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}
