package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import utils.ui.ViewUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class StartController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    public void relax(ActionEvent event) {
        ViewUtil.showGandalf();
    }

}
