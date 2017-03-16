package service.student;

import model.Book;
import model.Student;

import java.util.List;

/**
 * Created by Vadim on 15.03.2017.
 */
interface IStudentService {

    void saveStudent(Student student);

    void deleteStudent(Student student);

    void updateStudent(Student student);

    void addStudentToBlacklist(Student student);

    void deleteStudentFromBlackList(Student student);

    Student getStudentById(int id);

    List<Student> getAllStudents();
    List<Student> getStudentsBlacklist();

    List<Book> getStudentBookList(Student student);
}
