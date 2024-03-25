package main.gui;

import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Constants;
import main.MainServer;
import main.entities.ClientConnection;
import main.handlers.ClientConnectionHandler;
import utilities.SceneController;

/**
 * Controls the main server frame UI and handles interactions within the server application GUI.
 * This class manages the server's start/stop functionality, displays server and database status,
 * and handles the import of data. It extends the JavaFX Application class and implements Initializable
 * for initializing controller after the root element has been processed.
 */
public class MainServerFrameController extends Application implements Initializable{
	
	@FXML
	private Label serverStatusLabel;	
	@FXML
	private Label serverIPLabel;
	@FXML
	private Label DBStatusLabel;
	@FXML
	private TextField serverPortField;
	@FXML
	private TextField dbNameField;
	@FXML
	private TextField dbUserField;
	@FXML
	private TextField dbPassField;
	@FXML
	private Label msgLabel;
	@FXML
	private Button stopServerBtn;
	@FXML
	private Button startServerBtn;
	@FXML
	private TableView<ClientConnection> connectionTable;
	@FXML
	private TableColumn<ClientConnection, String> ipCol;
	@FXML
	private TableColumn<ClientConnection, String> hostCol;
	@FXML
	private TableColumn<ClientConnection, String> statusCol;
	@FXML
	private Pane headerPane;
	@FXML
	private Button importBtn;

	/**
	 * Saving offset x and the stage to apply drag functionality on the header
	 */
	private static double xOffset = 0;
    /**
     * Saving offset y and the stage to apply drag functionality on the header
     */
    private static double yOffset = 0;
    private static Stage stage;
    public static boolean isImport;

	/**
	 *
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		primaryStage.initStyle(StageStyle.UNDECORATED);
		SceneController scene = new SceneController();
		scene.changeScene("GoNature - Server", primaryStage, "/main/gui/MainServer.fxml");
	}

	/**
	 * Sets up the initial UI elements, including server IP, port, and database information.
	 */
	public void setUpUI() {
		try {
			serverIPLabel.setText(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			System.out.println("[MainServerFrameController] - error getting local host address");
			e.printStackTrace();
		}
		serverPortField.setText(Constants.DEFAULT_PORT);
		dbNameField.setText(Constants.DB_NAME);
		dbUserField.setText(Constants.DB_USERNAME);
	}
	
	/**
	 * Exits the server application.
	 * @param e The action event triggering the exit.
	 */
	public void exitServer(ActionEvent e) {
		System.out.println("[MainServerFrameController] - Exiting server");
		System.exit(0);
	}
	
	/**
	 * Initializes the controller class. This method is automatically called
	 * when we start the server
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setUpUI();
		headerPane.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() + xOffset);
                stage.setY(event.getScreenY() + yOffset);			
			}
			
		});
		
		headerPane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = stage.getX() - event.getScreenX();
                yOffset = stage.getY() - event.getScreenY();
            }
        });
		
	}
	
	/**
	 * Starts the server using the provided server and database configuration.
	 * Displays an error message if required fields are not filled or if the server fails to start.
	 * 
	 * @param event The action event triggering the server start.
	 */
	public void startServer(ActionEvent event) {
		if (!(serverPortField.getText().length() > 0 || dbNameField.getText().length() > 0 ||
			dbUserField.getText().length() > 0 || dbPassField.getText().trim().length() > 0)) {
			displayErrorMsg("Please fill all fields to start the server.", 195);
			return;
		}
		try {
			Constants.SERVER_PORT = Integer.parseInt(serverPortField.getText());
		}catch(Exception ex) {
			System.out.println("[MainServerFrameController] - inputted port is not an integer");
			return;
		}
		
		MainServer.startServer(serverPortField.getText(), dbNameField.getText(), dbUserField.getText(), dbPassField.getText());
		if (MainServer.serverStarted) {
			ClientConnectionHandler.clearConnectionTable();
			lockFieldsAfterStart();
			connectionTable.setItems(MainServer.getConnections());
			
			ipCol.setCellValueFactory(new PropertyValueFactory<ClientConnection, String>("clientIp"));
			hostCol.setCellValueFactory(new PropertyValueFactory<ClientConnection, String>("hostName"));
			statusCol.setCellValueFactory(new PropertyValueFactory<ClientConnection, String>("connectionStatus"));	
		}
		else
			displayErrorMsg("Server failed to start", -1);
	}
	
	/**
	 * Displays an error message on the GUI.
	 * 
	 * @param msg The error message to display.
	 * @param x he x-coordinate for positioning the message label, if applicable.
	 */
	public void displayErrorMsg(String msg, int x) {
		msgLabel.setText(msg);
		msgLabel.setTextFill(Color.valueOf("red"));
		if (x != -1)
			msgLabel.setLayoutX(x);
		msgLabel.setVisible(true);
	}
	
	/**
	 * Locks input fields after the server has started and updates the UI to reflect the server status.
	 */
	public void lockFieldsAfterStart() {
		if (MainServer.serverStarted) {
			serverPortField.setDisable(true);
			dbNameField.setDisable(true);
			dbUserField.setDisable(true);
			dbPassField.setDisable(true);
			
			serverStatusLabel.setText("Online");
			serverStatusLabel.setTextFill(Color.valueOf("#00ff22"));
			if (MainServer.dbConnection != null) {
				DBStatusLabel.setText("Connected");
				DBStatusLabel.setTextFill(Color.valueOf("#00ff22"));
			}else
				DBStatusLabel.setTextFill(Color.valueOf("red"));
			
			startServerBtn.setDisable(true);
			msgLabel.setVisible(false);
			
		}
	}
	
	/**
	 * Stops the server and re-enables input fields for server configuration.
	 * @param event The action event triggering.
	 */
	public void stopServer(ActionEvent event) {
		if (!MainServer.serverStarted) {
			displayErrorMsg("You can not stop the server when it is offline", 175);
			return;
		}
		ClientConnectionHandler.clearConnectionTable();
		serverPortField.setDisable(false);
		dbNameField.setDisable(false);
		dbUserField.setDisable(false);
		dbPassField.setDisable(false);
		serverStatusLabel.setText("Offline");
		serverStatusLabel.setTextFill(Color.valueOf("red"));
		DBStatusLabel.setText("Offline");
		DBStatusLabel.setTextFill(Color.valueOf("red"));
		MainServer.getInstance().closeConnection();
		
		startServerBtn.setDisable(false);
		importBtn.setDisable(false);
		isImport=false;
	}
	
	/**
	 * Initiates data import if the server is online, shows an error otherwise.
	 * @param event Action event triggering this method.
	 */
	public void importData(ActionEvent event) {
		if (!MainServer.serverStarted) {
			displayErrorMsg("You can not import data when the server offline", 175);
			return;
		}
		isImport=true;
		importBtn.setDisable(true);
	}
	
	/**
	 * Closes the server connection if online, then exits the application.
	 * @param event Mouse event triggering this method. 
	 */
	public void exitButton(MouseEvent event) {
		if (MainServer.serverStarted)
			MainServer.getInstance().closeConnection();
		System.exit(0);
	}

}
