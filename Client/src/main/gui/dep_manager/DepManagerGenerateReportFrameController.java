//shady built this class 10/3/2024 20:16


package main.gui.dep_manager;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DepManagerGenerateReportFrameController extends Application{
		public static void main(String[] args) {
			launch(args);
		}
		
		 @FXML
		    private Button btn_cancellation_report;

		    @FXML
		    private Button btn_show_report_data;

		    @FXML
		    private Button btn_visitor_report;

		    @FXML
		    private TextField feild_name;

		    @FXML
		    private TextField field_date;

		    @FXML
		    private TextField field_park;

		    @FXML
		    private Text text_date;

		    @FXML
		    private Text text_name;

		    @FXML
		    private Text text_park;

		    @FXML
		    void field_name(ActionEvent event) {

		    }

		
		@Override
		public void start(Stage primaryStage) throws Exception {
			Parent root = new Pane();
			try {//trying to load the first gui (startUpGui)
				root = FXMLLoader.load(getClass().getResource("DepManagerGenerateReportFrame.fxml"));
			}catch(Exception e) {
				System.out.println("[DepManagerGenerateReportFrameController] - Error loading DepManagerMainFrame.fxml");
				e.printStackTrace();
				System.exit(1);
			}
			//setting the root to the loaded fxml file and showing the gui
			Scene scene = new Scene(root);
			primaryStage.setTitle("GoNature Client");
			primaryStage.setScene(scene);
			
			primaryStage.show();			
		}

	}


