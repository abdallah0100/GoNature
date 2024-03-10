package main.gui.parkmanager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EditParkVariablesController  extends Application {
	 	@FXML
	    private Button EditEstimatedTime;

	    @FXML
	    private Button EditVisitor;

	    @FXML
	    private Button edit;

	    @FXML
	    private TextField oldValue;

	    @FXML
	    private TextField newValue;

	    @FXML
	    private Button editGap;
	    
	    public static void main(String[] args) {
			launch(args);
		}
	    @Override
		public void start(Stage primaryStage) throws Exception {
			Parent root = new Pane();
			try {//trying to load the first gui (startUpGui)
				root = FXMLLoader.load(getClass().getResource("EditParkVariables.fxml"));
			}catch(Exception e) {
				System.out.println("[EditParkVariables] - Error loading ClientStartUpFrame.fxml");
				e.printStackTrace();
				System.exit(1);
			}
			//setting the root to the loaded fxml file and showing the gui
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			
			primaryStage.show();
		}
	    
	    public void editEstimatedTime(ActionEvent e)
	    {
	    	
	    }
	    
	    public void editGap(ActionEvent e )
	    {
	    	
	    }

	    public void edit(ActionEvent e )
	    {
	    	
	    }
	    
	    public void editVisitor(ActionEvent e)
	    {
	    	
	    }
}
