package main.gui.dep_manager;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import entities.VisitsReport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import main.ClientController;
import main.ClientUI;
import main.controllers.VisitsGraphFrameController;
import utilities.SceneController;

/**
 * Controller class for managing the Select Visits Details Frame.
 */
public class SelectVisitsDetailsFrameController implements Initializable {

    @FXML
    private ComboBox<String> parkComboBox;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Pane hiddenPane;

    @FXML
    private Label errorMsg;

    private static VisitsReport[] ReturnedTimesData;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Iterator<String> iterator = ClientController.getParks().keySet().iterator();
        ArrayList<String> al = new ArrayList<>();
        while (iterator.hasNext()) {
            al.add(iterator.next());
        }
        ObservableList<String> list = FXCollections.observableArrayList(al);
        parkComboBox.setItems(list);
    }

    /**
     * Shows the graph window after selecting the park and date.
     * @param event the action event
     */
    @FXML
    public void showGraphWindow(ActionEvent event) {
        errorMsg.setVisible(false);

        if (parkComboBox.getValue() == null || datePicker.getValue() == null) {
            errorMsg.setText("Fill All Fields");
            errorMsg.setVisible(true);
        } else {
            String year = datePicker.getValue().getYear() + "";
            String month = datePicker.getValue().getMonthValue() + "";
            String day = datePicker.getValue().getDayOfMonth() + "";
            String parkName = parkComboBox.getValue();

            String[] listToSend = new String[2];
            String dateNewSyntax = year + "-" + month + "-" + day;
            listToSend[0] = dateNewSyntax;
            listToSend[1] = parkName;

            VisitsGraphFrameController.getVisitsDetails(listToSend);
            VisitsReportGraphFrameController.setReturnedTimesData(ReturnedTimesData);

            SceneController sc = new SceneController();
            sc.setPane(ClientUI.contentPane, "/main/gui/dep_manager/VisitsReportGraphFrame.fxml");
        }
    }

    /**
     * Sets the returned visit times data.
     * 
     * @param returnedTimesData the visit times data
     */
    public static void setReturnedTimesData(VisitsReport[] returnedTimesData) {
        SelectVisitsDetailsFrameController.ReturnedTimesData = returnedTimesData;
    }
}