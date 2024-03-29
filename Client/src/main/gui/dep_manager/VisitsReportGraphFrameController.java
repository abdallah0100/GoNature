package main.gui.dep_manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import entities.VisitsReport;
import java.net.URL;
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

/**
 * Controller class for managing the Visits Report Graph Frame.
 */
public class VisitsReportGraphFrameController implements Initializable {

    @FXML
    private ScatterChart<String, String> scatterChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private CategoryAxis yAxis;

    private static VisitsReport[] ReturnedTimesData;

    
    
    /**
     * initialize the visits graph with details for the chosen park.
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        xAxis.setTickLabelFill(Color.WHITE);
        yAxis.setTickLabelFill(Color.WHITE);
        xAxis.setTickLabelFont(new javafx.scene.text.Font(12));
        yAxis.setTickLabelFont(new javafx.scene.text.Font(12));

        XYChart.Series<String, String> seriesOrg = new XYChart.Series<>();
        XYChart.Series<String, String> seriesInd = new XYChart.Series<>();
        seriesOrg.setName("Organized Group");
        seriesInd.setName("Individuals");

        // Create a list of TimePair objects
        List<TimePair> timePairs = new ArrayList<>();
        for (VisitsReport visitReport : ReturnedTimesData) {
            timePairs.add(new TimePair(visitReport.getEnterHour(), visitReport.getEnterMin(),
                    visitReport.getExitHour(), visitReport.getExitMin(), visitReport.getReservationType()));
        }

        // Sort the list of TimePair objects based on enter times
        Collections.sort(timePairs, new TimePair.TimeComparator());

        for (TimePair pair : timePairs) {
            String enterTime = pair.getEnterHour() + ":" + (pair.getEnterMin() < 10 ? "0" + pair.getEnterMin() : pair.getEnterMin());
            String exitTime = pair.getExitHour() + ":" + (pair.getExitMin() < 10 ? "0" + pair.getExitMin() : pair.getExitMin());
            String visitTimeStr = pair.getVisitTime();

            // Create the data point with enterTime on the x-axis and exitTime on the y-axis
            XYChart.Data<String, String> dataPoint = new XYChart.Data<>(exitTime, enterTime);
            dataPoint.setNode(createDataNode(visitTimeStr));

            if ("Private".equals(pair.getReservationType())) {
                seriesInd.getData().add(dataPoint);
            } else {
                seriesOrg.getData().add(dataPoint);
            }
        }

        // Add series to scatter chart
        scatterChart.getData().add(seriesInd);
        scatterChart.getData().add(seriesOrg);
    }

    /**
    * Creates a StackPane node with a label for displaying visit time inside the node.
    * @param visitTime The visit time to be displayed.
    * @return A StackPane node containing the visit time label.
    */
    private StackPane createDataNode(String visitTime) {
        StackPane stackPane = new StackPane();
        Label label = new Label(visitTime);
        label.setTextFill(Color.WHITE); // Set text color to white
        stackPane.getChildren().add(label);
        return stackPane;
    }

    /**
    * Custom class to hold enter and exit times.
    */
    static class TimePair {
        private int enterHour;
        private int enterMin;
        private int exitHour;
        private int exitMin;
        private String reservationType;
        private String visitTime;

        public TimePair(int enterHour, int enterMin, int exitHour, int exitMin, String reservationType) {
            this.enterHour = enterHour;
            this.enterMin = enterMin;
            this.exitHour = exitHour;
            this.exitMin = exitMin;
            this.reservationType = reservationType;

            // Calculate visit time duration
            LocalTime enterTime = LocalTime.of(enterHour, enterMin);
            LocalTime exitTime = LocalTime.of(exitHour, exitMin);
            Duration duration = Duration.between(enterTime, exitTime);
            long hours = duration.toHours();
            long minutes = duration.minusHours(hours).toMinutes();
            this.visitTime = hours + ":" + (minutes < 10 ? "0" + minutes : minutes);
        }

        // Getters for enter and exit times
        public int getEnterHour() {
            return enterHour;
        }

        public int getEnterMin() {
            return enterMin;
        }

        public int getExitHour() {
            return exitHour;
        }

        public int getExitMin() {
            return exitMin;
        }

        public String getReservationType() {
            return reservationType;
        }

        public String getVisitTime() {
            return visitTime;
        }

        /**
        * Custom comparator class for sorting times according to entry time.
        */
        static class TimeComparator implements Comparator<TimePair> {
            @Override
            public int compare(TimePair pair1, TimePair pair2) {
                if (pair1.enterHour == pair2.enterHour) {
                    return Integer.compare(pair1.enterMin, pair2.enterMin);
                }
                return Integer.compare(pair1.enterHour, pair2.enterHour);
            }
        }
    }

    /**
     * Sets the returned visits report data.
     * @param returnedTimesData the array of visits report data
     */
    public static void setReturnedTimesData(VisitsReport[] returnedTimesData) {
        VisitsReportGraphFrameController.ReturnedTimesData = returnedTimesData;
    }
}