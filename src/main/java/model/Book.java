package model;


/**
 * Created by Vadim on 08.11.2016.
 */
public class Book {

    private int id;
    private String title;
    private String author;
    private String edition;
    private String yearOfPublication;
    private String dateOfGive;
    private String dateOfTake;
    private boolean available;
    private int studentId;

    public Book() {
    }

    public Book(String title, String author, String edition, String yearOfPublication) {
        this.title = title;
        this.author = author;
        this.edition = edition;
        this.yearOfPublication = yearOfPublication;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(String yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public String getDateOfGive() {
        return dateOfGive;
    }

    public void setDateOfGive(String dateOfGive) {
        this.dateOfGive = dateOfGive;
    }

    public String getDateOfTake() {
        return dateOfTake;
    }

    public void setDateOfTake(String dateOfTake) {
        this.dateOfTake = dateOfTake;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String toStringForSearch() {
        return "" + id + " " + title + " " + author + " " + edition + " " + yearOfPublication;
    }
}
