package controllers;

import controllers.book.tabs.BooksAvailableController;
import controllers.book.tabs.BooksController;
import controllers.book.tabs.BooksNotAvailableController;
import controllers.student.tabs.StudentsBlacklistController;
import controllers.student.tabs.StudentsController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;

import java.net.URL;
import java.util.ResourceBundle;

public class StartViewController implements Initializable {
    @FXML
    private Tab tabBooksAvailable, tabBooksNotAvailable, tabBooks, tabStudents, tabStudentsBlackList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTabBooksAvailableListener();
        setTabBooksListener();
        setTabBooksNotAvailableListener();
        setTabStudentsListener();
        setTabStudentsBlackListListener();
    }

    private void setTabBooksAvailableListener() {
        tabBooksAvailable.setOnSelectionChanged(event -> {
            BooksAvailableController.getInstance().initTableData();
        });
    }

    private void setTabBooksListener() {
        tabBooks.setOnSelectionChanged(event -> {
            BooksController.getInstance().initTableData();
        });
    }

    private void setTabBooksNotAvailableListener() {
        tabBooksNotAvailable.setOnSelectionChanged(event -> {
            BooksNotAvailableController.getInstance().initTableData();
        });
    }

    private void setTabStudentsListener() {
        tabStudents.setOnSelectionChanged(event -> {
            StudentsController.getInstance().initTableData();
        });
    }

    private void setTabStudentsBlackListListener() {
        tabStudentsBlackList.setOnSelectionChanged(event -> {
            StudentsBlacklistController.getInstance().initTableData();
        });
    }
}
