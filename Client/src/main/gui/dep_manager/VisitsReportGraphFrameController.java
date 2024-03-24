package main.gui.dep_manager;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;
import entities.VisitsReport;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.time.LocalTime;
import java.time.Duration;

public class VisitsReportGraphFrameController implements Initializable{

	   @FXML
	   private ScatterChart<String, String> scatterChart;
	   @FXML
	   private CategoryAxis xAxis;
	   @FXML
	   private CategoryAxis yAxis;
	
	   private static VisitsReport[] ReturnedTimesData;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Create scatterChart and Insert data into it	

		
		xAxis.setTickLabelFill(Color.WHITE);
		yAxis.setTickLabelFill(Color.WHITE);
		xAxis.setTickLabelFont(new javafx.scene.text.Font(12)); 
	    yAxis.setTickLabelFont(new javafx.scene.text.Font(12));
	        
	    XYChart.Series<String, String> seriesOrg = new XYChart.Series<>();
	    XYChart.Series<String, String> seriesInd = new XYChart.Series<>();
	    seriesOrg.setName("Organized Group");
	    seriesInd .setName("Indivisuals");
	    
  	
	    //sort times 
	    ArrayList<String> visitTimes = new ArrayList<String>();
	    ArrayList<String> sortedEnterTimes = new ArrayList<String>(); 
	    ArrayList<String> sortedExitTimes = new ArrayList<String>(); 
	    ArrayList<String> typeTimes = new ArrayList<String>();
	      
	    for(VisitsReport visitReport : ReturnedTimesData) {		   
	    	// if min in {0,...,9} so we add hour:0min
	    	if(visitReport.getEnterMin()%10 == visitReport.getEnterMin()) {
	    		sortedEnterTimes.add(visitReport.getEnterHour()+":0"+visitReport.getEnterMin());
	    	}
	    	else {
	    		sortedEnterTimes.add(visitReport.getEnterHour()+":"+visitReport.getEnterMin());
	    	}
	    	 typeTimes.add(visitReport.getReservationType());	    	
	    	 LocalTime enterTime = LocalTime.of(visitReport.getEnterHour(), visitReport.getEnterMin());
	         LocalTime exitTime = LocalTime.of(visitReport.getExitHour(), visitReport.getExitMin());
	         Duration duration = Duration.between(enterTime, exitTime);
	         // Convert duration to hours and minutes
	         long hours = duration.toHours();
	         long minutes = duration.minusHours(hours).toMinutes();
	         String visitTimeStr = hours + ":" + minutes;
	         visitTimes.add(visitTimeStr);
	    }
	    

        Collections.sort(sortedEnterTimes, new TimeComparator());
	    

	    for(VisitsReport visitReport : ReturnedTimesData) {		   
	    	// if minute in {0,...,9} so we add hour:0min
	    	if(visitReport.getExitMin()%10 == visitReport.getExitMin()) {
	    		sortedExitTimes .add(visitReport.getExitHour()+":0"+visitReport.getExitMin());
	    	}
	    	else {
	    		sortedExitTimes .add(visitReport.getExitHour()+":"+visitReport.getExitMin());
	    	}
	    }
	   

        Collections.sort(sortedExitTimes, new TimeComparator());
        
        for (int i = 0; i < sortedEnterTimes.size(); i++) {
            String enterTime = sortedEnterTimes.get(i);
            String exitTime = sortedExitTimes.get(i);
            if("Private".equals(typeTimes.get(i))) {
	            //Create the data point with enterTime on the x-axis and exitTime on the y-axis
	            XYChart.Data<String, String> dataPoint = new XYChart.Data<>(exitTime, enterTime);
	           
	            dataPoint.setNode(createDataNode(visitTimes.get(i)));
	            seriesInd.getData().add(dataPoint);
            }
            else {
            	XYChart.Data<String, String> dataPoint = new XYChart.Data<>(exitTime, enterTime);
	            dataPoint.setNode(createDataNode(visitTimes.get(i)));

	            seriesOrg.getData().add(dataPoint);
            }
        }
        
        //Add series to scatter chart
        scatterChart.getData().add(seriesInd);
        scatterChart.getData().add(seriesOrg);     
	}	
	 private StackPane createDataNode(String visitTime) {
	        StackPane stackPane = new StackPane();
	        Label label = new Label(visitTime);
	        label.setTextFill(Color.WHITE); // Set text color to red
	        stackPane.getChildren().add(label);
	        return stackPane;
	    }

	// Custom comparator class for sorting times
	static class TimeComparator implements Comparator<String> {
	    @Override
	    public int compare(String time1, String time2) {
	        String[] parts1 = time1.split(":");
	        String[] parts2 = time2.split(":");
	        int hour1 = Integer.parseInt(parts1[0]);
	        int min1 = Integer.parseInt(parts1[1]);
	        int hour2 = Integer.parseInt(parts2[0]);
	        int min2 = Integer.parseInt(parts2[1]);
	        if (hour1 == hour2) {
	            return Integer.compare(min1, min2);
	        }
	        return Integer.compare(hour1, hour2);
	    }
	}
	
	public static void getReturnedTimesData(VisitsReport[] returnedTimesData) {
		VisitsReportGraphFrameController.ReturnedTimesData = returnedTimesData;
	}
}			