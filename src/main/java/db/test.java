package db;


import model.Book;
import model.Student;

import java.util.List;

/**
 * Created by Vadim on 27.02.2017.
 */
public class test {


    public static void main(String[] args) {

        StudentDAO studentDAO = new StudentDAO();
        Student student = studentDAO.getStudentById(1);
        studentDAO.deleteFromBlackList(student);

//        studentDAO.update(student);

//        System.out.println(studentDAO.getStudentById(9).getName());

        List<Book> books = studentDAO.getStudentBookList(studentDAO.getStudentById(4));
        for (int i = 0; i < books.size(); i++) {
            Book book1 = books.get(i);
//            Student student1 = students.get(i);
            System.out.println(book1.getTitle());}
//        studentDAO.saveInBlackList(studentDAO.getStudentById(1));
//        studentDAO.deleteFromBlackList(studentDAO.getStudentById(1));
//        System.out.println(studentDAO.getStudentById(1).isBlacklist());
//        bookDAO.give(bookDAO.getBookById(1),studentDAO.getStudentById(4));
//        bookDAO.give(bookDAO.getBookById(2),studentDAO.getStudentById(3));
//        bookDAO.give(bookDAO.getBookById(3),studentDAO.getStudentById(4));
//        bookDAO.give(bookDAO.getBookById(4),studentDAO.getStudentById(4));
//        bookDAO.give(bookDAO.getBookById(13),studentDAO.getStudentById(1));
//        System.out.println(studentDAO.getStudentBookList(studentDAO.getStudentById(4)));

    }
}
