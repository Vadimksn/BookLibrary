package main;

import javafx.application.Application;
import javafx.stage.Stage;
import utils.ui.UiConstants;
import utils.ui.ViewUtil;

public class StartFxApp extends Application {

    private static StartFxApp instance;


    public static StartFxApp getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        instance = this;
        ViewUtil.showView(UiConstants.Path.START_PATH, UiConstants.Tittle.START_TITTLE, true);



    }
}
