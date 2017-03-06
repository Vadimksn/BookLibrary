package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Vadim on 08.11.2016.
 */
public class Student {

    private int id;
    private String name;
    private String surname;
    private String middleName;
    private String passportData;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private String registrationDate = dateFormat.format(new Date());
    private boolean blacklist;
    private String blacklistDate="";
    private List<Book> bookList;

    public Student() {
    }

    public Student(String name, String surname, String middleName, String passportData) {
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.passportData = passportData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPassportData() {
        return passportData;
    }

    public void setPassportData(String passportData) {
        this.passportData = passportData;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean isBlacklist() {
        return blacklist;
    }

    public void setBlacklist(boolean blacklist) {
        this.blacklist = blacklist;
    }

    public String getBlacklistDate() {
        return blacklistDate;
    }

    public void setBlacklistDate(String blacklistDate) {
        this.blacklistDate = blacklistDate;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
