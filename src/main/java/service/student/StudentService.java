package service.student;

import db.StudentDAO;
import model.Book;
import model.Student;

import java.util.List;

public class StudentService implements IStudentService {
    private StudentDAO studentDAO = new StudentDAO();

    @Override
    public boolean saveStudent(Student student) {
        return studentDAO.save(student);
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
    public List<Student> getStudentsBlacklist() {
        return studentDAO.getStudentsBlacklist();
    }

    @Override
    public Student getLastAddedStudent() {
        return studentDAO.getLastAddedStudent();
    }

}
