package mgr.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import mgr.model.dao.impl.ProductoIDAO;
import mgr.model.entities.ProductoEntity;
import mgr.utils.viewsutils.Alerts;
import mgr.utils.viewsutils.stringconverters.ProductoConverter;

public class DemandController extends FXController implements Initializable {
    @FXML
    private JFXButton backBtn;

    @FXML
    private JFXComboBox<ProductoEntity> productsCmb;

    @FXML
    private LineChart<?, ?> qLineChart;

    @FXML
    private Label ddlbl;

    @FXML
    private Label qoptlbl;

    @FXML
    private Label calbl;

    @FXML
    private Label noalbl;

    @FXML
    private Label roplbl;

    @FXML
    private Label avgivlbl;

    @FXML
    private Label maxivlbl;


    public void handleBack(){
        this.mainApp.showHome();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //cargar productos en el combobox
        try {
            ProductoIDAO pidao = new ProductoIDAO();
            List<ProductoEntity> productos = pidao.findAll();
            ObservableList<ProductoEntity> prods = FXCollections.observableArrayList(productos);
            productsCmb.setItems(prods);
            productsCmb.setConverter(new ProductoConverter());
        } catch (Exception e) {
            //Forma de imprimir excepciones usando el alert
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            Alerts.exceptionAlert(e.getMessage(), sw.toString());
        }

    }
}
