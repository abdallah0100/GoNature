package main.gui.dep_manager; 

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import main.controllers.DataForAnalyingController;


public class CancellationsGraphFrameController implements Initializable{
	
	private  static String year;
	private  static String month;
	private  static String day;
	private  static String parkName;
	@FXML
	private PieChart pieChart = new PieChart();;
	private static int numOfCancellations;
	private static int numOfNotActivated;
	private static int numOfReservations;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String[] listToSend = new String[2];
		String dateNewSyntax = year + "-" + month + "-" + day;
		listToSend[0] = dateNewSyntax;	
		listToSend[1] = parkName;
		//Set values for numOfCancellations, numOfNotActivated, numOfReservations
		DataForAnalyingController.getNumOfCancellations(listToSend);
		
		//Prevent dividing by 0
		if(numOfReservations == 0)
		{
		  PieChart.Data emptyPie = new PieChart.Data("No Reservations", 1);
		  pieChart.getData().add(emptyPie);
		  return;
		}

		double CancellationsPrecentage = (double)((double)numOfCancellations/numOfReservations)*100;
		double NotActivatedPrecentage = (double)((double)numOfNotActivated/numOfReservations)*100;
		double averagePrecentage =  (double)(100 -  numOfCancellations - numOfReservations);

	    String cancelledReservationsStr = String.format("Number Of Cancelled Reservations = %d -> %.2f%%",numOfCancellations, CancellationsPrecentage );
		String notActivatedReservationStr = String.format("Number Of Not Activated Reservations = %d -> %.2f%%",numOfNotActivated, NotActivatedPrecentage);

		PieChart.Data cancelledSlice = new PieChart.Data(cancelledReservationsStr, CancellationsPrecentage);
		PieChart.Data notActivatedSlice = new PieChart.Data(notActivatedReservationStr, NotActivatedPrecentage);
		PieChart.Data totalReservationsSlice = new PieChart.Data("Total Reservations = " + numOfReservations,averagePrecentage);

		pieChart.getData().add(cancelledSlice);
		pieChart.getData().add(notActivatedSlice);
		pieChart.getData().add(totalReservationsSlice);
	}
	
	public static void setValues(int numOfCancellations,int numOfNotActivated,int numOfReservations) {
		CancellationsGraphFrameController.numOfCancellations = numOfCancellations;
		CancellationsGraphFrameController.numOfNotActivated = numOfNotActivated;
		CancellationsGraphFrameController.numOfReservations = numOfReservations;
	}

	public static void setYear(String year) {
		CancellationsGraphFrameController.year = year;
	}


	public static void setMonth(String month) {
		CancellationsGraphFrameController.month = month;
	}

	public static void setDay(String day) {
		CancellationsGraphFrameController.day = day;
	}

	public static void setParkName(String parkName) {
		CancellationsGraphFrameController.parkName = parkName;
	}
	
}