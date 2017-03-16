package controllers;

import controllers.book.tabs.BooksAvailableController;
import controllers.book.tabs.BooksController;
import controllers.book.tabs.BooksNotAvailableController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Vadim on 15.03.2017.
 */
public class StartViewController implements Initializable{
    @FXML
    private Tab tabBooksAvailable,tabBooksNotAvailable,tabBooks,tabStudents,tabStudentsBlackList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTabBooksAvailableListener();
        setTabBooksListener();
        setTabBooksNotAvailable();
    }

    private void setTabBooksAvailableListener(){
        tabBooksAvailable.setOnSelectionChanged(event ->{
            BooksAvailableController.getInstance().initTableData();
        });
    }

    private void setTabBooksListener(){
        tabBooks.setOnSelectionChanged(event ->{
            BooksController.getInstance().initTableData();
        });
    }

    private void setTabBooksNotAvailable(){
        tabBooksNotAvailable.setOnSelectionChanged(event ->{
            BooksNotAvailableController.getInstance().initTableData();
        });
    }
}
