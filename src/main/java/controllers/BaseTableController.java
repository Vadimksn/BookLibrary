package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.LibraryModel;
import org.apache.log4j.Logger;
import service.book.BookService;
import service.student.StudentService;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseTableController<T extends LibraryModel> {

    protected BookService bookService = new BookService();
    protected StudentService studentService = new StudentService();
    protected ObservableList<T> observableList;

    protected abstract TableView<T> getTableView();

    protected abstract TextField getTextFieldSearch();

    public abstract void initTableData();

    private int getSelectedId() {
        return getTableView().getSelectionModel().getSelectedIndex();
    }

    public T getSelectionItem() {
        int id = getSelectedId();
        if (id != -1) {
            return observableList.get(id);
        } else return null;
    }

    protected void search() {
        String stringForSearch = getTextFieldSearch().getText();

        if (stringForSearch.isEmpty()) {
            getTableView().setItems(observableList);
        } else {
            List<T> models = new ArrayList<>();
            for (T model : observableList) {
                if (model.toStringForSearch().contains(stringForSearch))
                    models.add(model);
            }
            ObservableList<T> newList = FXCollections.observableArrayList(models);
            getTableView().setItems(newList);
        }
    }


}
