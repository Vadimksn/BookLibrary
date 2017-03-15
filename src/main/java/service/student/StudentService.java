package service.student;

import db.StudentDAO;
import model.Book;
import model.Student;

import java.util.List;

/**
 * Created by Vadim on 15.03.2017.
 */
public class StudentService implements IStudentService {
    StudentDAO studentDAO = new StudentDAO();

    @Override
    public void saveStudent(Student student) {
        studentDAO.save(student);
    }

    @Override
    public void deleteStudent(Student student) {
        studentDAO.delete(student);
    }

    @Override
    public void updateStudent(Student student) {
        studentDAO.update(student);
    }

    @Override
    public void addStudentToBlacklist(Student student) {
        studentDAO.addToBlackList(student);
    }

    @Override
    public void deleteStudentFromBlackList(Student student) {
        studentDAO.deleteFromBlackList(student);
    }

    @Override
    public Student getStudentById(int id) {
        return studentDAO.getStudentById(id);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDAO.getListAllStudents();
    }

    @Override
    public List<Book> getStudentBookList(Student student) {
        return studentDAO.getStudentBookList(student);
    }
}
