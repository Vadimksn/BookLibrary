package model;

public class Book  extends LibraryModel {

    private String title;
    private String author;
    private String edition;
    private String yearOfPublication;
    private String dateOfGive;
    private String dateOfTake;
    private Boolean available;
    private int studentId;

    public Book() {
    }

    public Book(String title, String author, String edition, String yearOfPublication) {
        this.title = title;
        this.author = author;
        this.edition = edition;
        this.yearOfPublication = yearOfPublication;
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

    public Boolean isAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
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
