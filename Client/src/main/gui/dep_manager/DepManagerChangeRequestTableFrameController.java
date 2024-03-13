package main.gui.dep_manager;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utilities.SceneController;

public class DepManagerChangeRequestTableFrameController extends Application {

    @FXML
    private AnchorPane sp;

    @FXML
    private TableView<?> tableView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        SceneController sceneController = new SceneController();
        sceneController.changeScene("GoNature - Department Manager", primaryStage,
                "/main/gui/dep_manager/DepManagerChangeRequestTableFrame.fxml");
    }
}

