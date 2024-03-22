package main.gui.dep_manager;

import java.net.URL;
import java.util.*;
import java.util.Map.Entry;
import entities.Park;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import main.ClientController;
import main.controllers.*;

public class DecideVarEditFrameController implements Initializable {
	@FXML
	private Button showBtn;
    @FXML
    private Label label1, label2, label3, label4, label5, label6, label7, label8, label9;
    @FXML
    private Button acceptFirstRow, acceptSecondRow, acceptThirdRow;
    @FXML
    private Button rejectFirstRow, rejectSecondRow,rejectThirdRow;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private Label editiedVariable, oldValue, newValue, errorMsg;
    
    private static HashMap<?, ?> returnedHashMap;
    private Label[] labels;
    private Button[] acceptButtons;
    private Button[] rejectButtons;
    private String[] keys = new String[3];
    private String[] values = new String[3];
    private String[] dataToDelete = new String[2];
    private int index;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labels = new Label[]{label1, label2, label3, label4, label5, label6, label7, label8, label9};
        acceptButtons = new Button[]{acceptFirstRow, acceptSecondRow, acceptThirdRow};
        rejectButtons = new Button[]{rejectFirstRow, rejectSecondRow, rejectThirdRow};

        Iterator<String> iterator = ClientController.getParks().keySet().iterator();
        ArrayList<String> al = new ArrayList<>();
        while (iterator.hasNext()) {
            al.add(iterator.next());
        }
        ObservableList<String> list = FXCollections.observableArrayList(al);
        comboBox.setItems(list);
    }

    public void comboBoxClicked(ActionEvent e) {
        errorMsg.setVisible(false);
        editiedVariable.setVisible(false);
        oldValue.setVisible(false);
        newValue.setVisible(false);
        refreshLabelsButtons();
        showBtn.setDisable(false);
    }

    public void deleteAndUpdateRequest(ActionEvent e) {
        processDeleteRequest(e, acceptButtons);
    }

    public void deleteRequest(ActionEvent e) {
        processDeleteRequest(e, rejectButtons);
    }

    private void processDeleteRequest(ActionEvent e, Button[] buttons) {
        dataToDelete[0] = comboBox.getValue();
        Button clickedButton = (Button) e.getSource();
        index = Arrays.asList(buttons).indexOf(clickedButton);
        dataToDelete[1] = labels[index * 3].getText();
        clickedButton.setDisable(true);
        DeleteRequestController.deleteRequestChange(dataToDelete);
        if (buttons == acceptButtons) {
            Park parkToEdit = getParkToEdit(dataToDelete[1], values[index]);
            ParkRequestHandler.updateVariable(parkToEdit);  
        }
        acceptButtons[index].setDisable(true);
        rejectButtons[index].setDisable(true); 
    }

    private Park getParkToEdit(String key, String value) {
        Park parkToEdit = ClientController.getParks().get(comboBox.getValue());
        parkToEdit.setVarbToUpdate(key);
        parkToEdit.setNewValue(Integer.parseInt(value));
        return parkToEdit;
    }

    public void showEditsDetails(ActionEvent e) {
        showBtn.setDisable(true);
        refreshLabelsButtons();
        if (comboBox.getValue() == null) {
            errorMsg.setText("Select park");
            errorMsg.setVisible(true);
        } else {
            errorMsg.setVisible(false);
            CheckEditedVarsController.sendShowEditedVars(comboBox.getValue());
            if (returnedHashMap.size() == 0) {
                errorMsg.setText("There Is No Edited Variables");
                errorMsg.setVisible(true);
            } else {
                editiedVariable.setVisible(true);
                oldValue.setVisible(true);
                newValue.setVisible(true);
                int counter = 0;
                for (Entry<?, ?> entry : returnedHashMap.entrySet()) {
                    keys[counter] = entry.getKey().toString();
                    values[counter] = entry.getValue().toString();
                    showRow(counter++, entry.getKey().toString(), entry.getValue().toString());
                }
            }
        }
    }

    private void showRow(int index, String key, String newValue) {
        labels[index * 3].setVisible(true);
        labels[index * 3 + 1].setVisible(true);
        labels[index * 3 + 2].setVisible(true);
        labels[index * 3].setText(key);
        setOldValue(labels[index * 3 + 1], key);
        labels[index * 3 + 2].setText(newValue);
        labels[index * 3].setVisible(true);
        labels[index * 3 + 1].setVisible(true);
        labels[index * 3 + 2].setVisible(true);
        labels[index * 3].setText(key);
        setOldValue(labels[index * 3 + 1], key);
        labels[index * 3 + 2].setText(newValue);
        acceptButtons[index].setVisible(true);
        rejectButtons[index].setVisible(true);
    }

    private void setOldValue(Label label, String key) {
        Park park = ClientController.getParks().get(comboBox.getValue());
        switch (key) {
            case "gap":
                label.setText(Integer.toString(park.getGap()));
                break;
            case "capacity":
                label.setText(Integer.toString(park.getMaxCapacity()));
                break;
            default:
                label.setText(Integer.toString(park.getEstimatedTime()));
        }
    }

    private void refreshLabelsButtons() {
    	 for (Label label : labels) {
             label.setVisible(false);
         }
         for (Button button : acceptButtons) {
             button.setVisible(false);
             button.setDisable(false);
         }
         for (Button button : rejectButtons) {
             button.setVisible(false);
             button.setDisable(false);
         }
    }

    public static void setReturnedHashMap(HashMap<?, ?> hashMap) {
        DecideVarEditFrameController.returnedHashMap = hashMap;
    }
}