package main.gui.dep_manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import main.ClientUI;
import utilities.SceneController;

/**
 * Controller class for managing the Select Report Frame.
 */
public class SelectReportFrameController {

    @FXML
    private Button numOfVisitorsReportsBtn;

    @FXML
    private Button cancellationsReportsBtn;

    /**
     * Opens a new window after clicking on the Usage Report button.
     *
     * @param event the action event
     */
    @FXML
    public void showUsageWindow(ActionEvent event) {
        SceneController sc = new SceneController();
        sc.setPane(ClientUI.contentPane, "/main/gui/dep_manager/UsageReportFrame.fxml");
    }

    /**
     * Opens a new window after clicking on the Number of Visitors Report button.
     *
     * @param event the action event
     */
    @FXML
    public void showNumOfVisitorsWindow(ActionEvent event) {
        SceneController sc = new SceneController();
        sc.setPane(ClientUI.contentPane, "/main/gui/dep_manager/ReportDetailsFrame.fxml");
    }
}
