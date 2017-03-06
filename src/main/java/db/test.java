package db;


/**
 * Created by Vadim on 27.02.2017.
 */
public class test {


    public static void main(String[] args) {
//        Date date = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DATE, 7);  // number of days to add
//        System.out.println(dateFormat.format(calendar.getTime()));
        BookDAO bookDAO = new BookDAO();
        StudentDAO studentDAO = new StudentDAO();
        System.out.println(bookDAO.getBookById(13).getTitle());
//        Book book = new Book("Barabolya 2","Oleg very Big","NikiEDITION",2015);
//        book.setId(13);
//        bookDAO.delete(bookDAO.getBookById(12));
//        bookDAO.save(book);
//        bookDAO.update(book);
//        bookDAO.give(book,student);
//        bookDAO.take(book);
//
//        List <Book> books = new ArrayList();
//        books= bookDAO.getListAllBooks();
//        for(int i=0;i<books.size();i++){
//            Book book1 = books.get(i);
//            System.out.println(book1.getAuthor());
//        }

//        Student student = new Student("Bob","Smith","Panasovych","X777");
//        studentDAO.save(student);
//        student.setId(9);
//        studentDAO.delete(student);
//        studentDAO.update(student);
//        System.out.println(studentDAO.getStudentById(3).isBlacklist());

//        List <Student> students = studentDAO.getListAllStudents();
//        for(int i=0;i<students.size();i++){
//            Student  student1= students.get(i);
//            System.out.println(student1.getName());
//        }
//        studentDAO.saveInBlackList(studentDAO.getStudentById(1));
//        studentDAO.deleteFromBlackList(studentDAO.getStudentById(1));
//        System.out.println(studentDAO.getStudentById(1).isBlacklist());
//        bookDAO.give(bookDAO.getBookById(1),studentDAO.getStudentById(4));
//        bookDAO.give(bookDAO.getBookById(2),studentDAO.getStudentById(3));
//        bookDAO.give(bookDAO.getBookById(3),studentDAO.getStudentById(4));
//        bookDAO.give(bookDAO.getBookById(4),studentDAO.getStudentById(4));
//        bookDAO.give(bookDAO.getBookById(13),studentDAO.getStudentById(1));
        System.out.println(studentDAO.getStudentBookList(studentDAO.getStudentById(4)));

    }


}
