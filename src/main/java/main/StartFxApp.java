package main;

import javafx.application.Application;
import javafx.stage.Stage;
import utils.ui.UiPathConstants;
import utils.ui.UiTitleConstants;
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
        ViewUtil.showView(UiPathConstants.START_PATH, UiTitleConstants.START_TITTLE);
    }
}
