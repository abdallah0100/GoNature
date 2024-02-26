package main.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.ClientController;
import main.ClientUI;
import main.client_requests.RequestHandler;
import main.commons.Order;
import main.commons.requests.RequestType;

public class ClientFrameController extends Application implements Initializable{
	
	@FXML
	private Pane dataPane;
	@FXML
	private ComboBox<String> ordersBox;
	@FXML
	private TextField parkNameField;
	@FXML
	private TextField timeField;
	@FXML
	private TextField emailField;
	@FXML
	private TextField numOfVisitorsField;
	@FXML
	private TextField telephoneField;
	@FXML
	private Label errorLabel;
	
	
	private void updateOrderBox()
	{
		ArrayList<String[]> data = ClientUI.clientController.getClient().getOrders();
		ArrayList<String> ordernums = new ArrayList<>();
		for (String[] row : data) {
			ordernums.add(row[1]);
		}
		
		ObservableList<String> list = FXCollections.observableArrayList(ordernums);
		ordersBox.setItems(list);
	}
	
	//a function to check if there was any error communicating with the server
	public boolean errorOccured() {
		if (!ClientController.connectedToServer) {
			errorLabel.setText("Error Connecting to the server");
			return true;
		}else if (!ClientController.fetchedData) {
			errorLabel.setText("Error fetching data from the database");
			return true;
		}
		return false;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (errorOccured()) 
			errorLabel.setVisible(true);
		else						
			updateOrderBox();//read the function that update the orderBox
	}

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		RequestHandler.requestOrderData();
		Parent root = new Pane();
		try {//trying to load the first gui (startUpGui)
			root = FXMLLoader.load(getClass().getResource("ClientFrame.fxml"));
		}catch(Exception e) {
			System.out.println("[ClientFrameController] - Error loading ClientFrame.fxml");
			e.printStackTrace();
			System.exit(1);
		}
		//setting the root to the loaded fxml file and showing the gui
		Scene scene = new Scene(root);
		primaryStage.setTitle("GoNature Client - demo");
		primaryStage.setScene(scene);
		
		primaryStage.show();		
	}
	
	public void showDataClick(ActionEvent e) throws Exception{
		if (ordersBox.getValue() != null) {
			String id = ordersBox.getValue();
			String[] order = ClientUI.clientController.getClient().getOrderById(id);
			if (order != null) {
				parkNameField.setText(order[0]);
				timeField.setText(order[2]);
				numOfVisitorsField.setText(order[3]);
				telephoneField.setText(order[4]);
				emailField.setText(order[5]);
				dataPane.setVisible(true);
			}
		}
	}
	
	
	public void updateData(ActionEvent e) throws Exception{
		Order o = new Order(parkNameField.getText(), ordersBox.getValue(), timeField.getText(),
				numOfVisitorsField.getText(), telephoneField.getText(), emailField.getText());
		ArrayList<String> data = o.getArray();
		data.add(0, RequestType.UPDATE.getRequestId() + "");
		ClientUI.clientController.accept(data);
		ArrayList<String> update = new ArrayList<>();
		update.add(RequestType.REQUEST_DATA.getRequestId() + "");
		ClientUI.clientController.accept(update);
		updateOrderBox();
	}
	
	
	public void exitClient(ActionEvent e) {
		System.out.println("[ClientFrameController] - Exiting Client");
		ClientUI.clientController.getClient().quit();
		System.exit(0);
	}
	
	
	
	

}
