package mgr.views;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class DemandController extends FXController implements Initializable {
    @FXML
    private JFXButton backBtn;

    @FXML
    private LineChart<?, ?> qLineChart;

    public void handleBack(){
        this.mainApp.showHome();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        XYChart.Series serie1 = new XYChart.Series();
        serie1.getData().add(new XYChart.Data<>("1", 5));
        serie1.getData().add(new XYChart.Data<>("2", 10));
        serie1.getData().add(new XYChart.Data<>("3", 5));
        serie1.getData().add(new XYChart.Data<>("4", 10));
        serie1.getData().add(new XYChart.Data<>("5", 5));
        serie1.getData().add(new XYChart.Data<>("6", 10));

        qLineChart.getData().addAll(serie1);
    }
}
