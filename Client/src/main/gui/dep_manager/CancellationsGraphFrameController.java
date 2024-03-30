package main.gui.dep_manager;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import main.controllers.DataForAnalyingController;

/**
 * Controller class for managing the Cancellations Graph Frame.
 */
public class CancellationsGraphFrameController implements Initializable {

    private static String year;
    private static String month;
    private static String day;
    private static String parkName;

    @FXML
    private PieChart pieChart = new PieChart();

    private static int numOfCancellations;
    private static int numOfNotActivated;
    private static int numOfReservations;

    
    /**
    * initialize the pie chart with the data and the distributions
    * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
    * @param resources The resources used to localize the root object, or null if the root object was not localized.
    */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] listToSend = new String[2];
        String dateNewSyntax = year + "-" + month + "-" + day;
        listToSend[0] = dateNewSyntax;
        listToSend[1] = parkName;

        // Set values for numOfCancellations, numOfNotActivated, numOfReservations
        DataForAnalyingController.getNumOfCancellations(listToSend);

       
        double cancellationsPercentage = ((double) numOfCancellations / numOfReservations) * 100;
        double notActivatedPercentage = ((double) numOfNotActivated / numOfReservations) * 100;
        double averagePercentage = 100 - cancellationsPercentage - notActivatedPercentage;

        String cancelledReservationsStr = String.format("Number Of Cancelled Reservations = %d -> %.2f%%", numOfCancellations, cancellationsPercentage);
        String notActivatedReservationStr = String.format("Number Of Not Activated Reservations = %d -> %.2f%%", numOfNotActivated, notActivatedPercentage);

        PieChart.Data cancelledSlice = new PieChart.Data(cancelledReservationsStr, cancellationsPercentage);
        PieChart.Data notActivatedSlice = new PieChart.Data(notActivatedReservationStr, notActivatedPercentage);
        PieChart.Data totalReservationsSlice = new PieChart.Data("Total Reservations for This Day = " + numOfReservations, averagePercentage);

        pieChart.getData().add(cancelledSlice);
        pieChart.getData().add(notActivatedSlice);
        pieChart.getData().add(totalReservationsSlice);
    }

    /**
     * Sets the values for the number of cancellations, number of not activated reservations, and total number of reservations.
     * 
     * @param numOfCancellations    the number of cancellations
     * @param numOfNotActivated     the number of not activated reservations
     * @param numOfReservations     the total number of reservations
     */
    public static void setValues(int numOfCancellations, int numOfNotActivated, int numOfReservations) {
        CancellationsGraphFrameController.numOfCancellations = numOfCancellations;
        CancellationsGraphFrameController.numOfNotActivated = numOfNotActivated;
        CancellationsGraphFrameController.numOfReservations = numOfReservations;
    }

    /**
     * Sets the year for the graph.
     * 
     * @param year The year that is chosen by the department manager
     */
    public static void setYear(String year) {
        CancellationsGraphFrameController.year = year;
    }

    /**
     * Sets the month for the graph.
     * 
     * @param month the month that is chosen by the department manager
     */
    public static void setMonth(String month) {
        CancellationsGraphFrameController.month = month;
    }

    /**
     * Sets the day for the graph.
     * 
     * @param day the day that is chosen by the department manager
     */
    public static void setDay(String day) {
        CancellationsGraphFrameController.day = day;
    }

    /**
     * Sets the park name for the graph.
     * 
     * @param parkName the park name that is chosen by the department manager
     */
    public static void setParkName(String parkName) {
        CancellationsGraphFrameController.parkName = parkName;
    }
}