package inv.mgr.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import inv.mgr.model.entities.DemandaEntity;
import inv.mgr.model.entities.ProductoEntity;
import inv.mgr.utils.productioncalcs.PModel;
import inv.mgr.utils.statutils.Statistic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.*;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import inv.mgr.model.dao.impl.DemandaIDAO;
import inv.mgr.model.dao.impl.ProductoIDAO;
import inv.mgr.utils.productioncalcs.QModel;
import inv.mgr.utils.viewsutils.Alerts;
import inv.mgr.utils.viewsutils.stringconverters.ProductoConverter;

public class DemandController extends FXController implements Initializable {
    @FXML
    private JFXButton backBtn;

    @FXML
    private JFXComboBox<ProductoEntity> productsCmb;

    @FXML
    private LineChart<?,?> qLineChart;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private NumberAxis yAxis;

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

    public void handleLoad(){
        ProductoEntity productSelected = productsCmb.getSelectionModel().getSelectedItem();
        if(productSelected == null) {
            Alerts.simpleAlert("Por favor seleccione un producto válido", 4);
            return;
        }
        if(productSelected.getTipoDemanda().equals("Constante"))
            demandaQ(productSelected);
        else
            demandaP(productSelected);

    }

    private void demandaQ(ProductoEntity prod) {
        //obtener demandas
        try {
            DemandaIDAO didao = new DemandaIDAO();
            DemandaEntity key = new DemandaEntity();
            key.setProductoId(prod.getProductoId());
            List<DemandaEntity> demandas = didao.findAll();
            int index = Collections.binarySearch(demandas, key, Comparator.comparingInt(DemandaEntity::getProductoId));
            if(index < 0) {
                Alerts.simpleAlert("No hay demanda registrada para este producto", 4);
                return;
            }
            key = demandas.get(index);

            if(key.getuTiempo().equals("mes")) {
                // se necesita demanda anual
                key.setCantidad(key.getCantidad() * 12);
            }
            QModel.staticInstance(Double.parseDouble(key.getCantidad().toString()), prod.getcH(), prod.getcS(),
                    prod.getcL(), null, prod.getCosto(), 360.0, null);
            Double qopt = QModel.getQoptimal(), rop = QModel.getROP(), qm = QModel.getLevelMax();

            ddlbl.setText(QModel.getDayDemand().toString());
            calbl.setText(QModel.getCostInYear().toString());
            noalbl.setText(QModel.getNOrderYear().toString());
            qoptlbl.setText(qopt.toString());
            roplbl.setText(rop.toString());
            avgivlbl.setText(QModel.getLevelAvarage().toString());
            maxivlbl.setText(qm.toString());

            drawLine(prod.getReserva(), rop, qopt, qm, QModel.getTimeBetweenOrder());
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            Alerts.exceptionAlert(e.getMessage(), sw.toString());
        }
    }

    private void demandaP(ProductoEntity prod) {
        //obtener demandas
        try {
            DemandaIDAO didao = new DemandaIDAO();
            List<DemandaEntity> demandas = didao.findAll();
            List<DemandaEntity> demandaProducto = new ArrayList<>();
            demandas.forEach(demandaEntity -> {
                if(demandaEntity.getProductoId() == prod.getProductoId()){
                    demandaProducto.add(demandaEntity);
                }
            });

            if(demandaProducto.size() <= 0) {
                Alerts.simpleAlert("No hay demanda registrada para este producto", 4);
                return;
            }

            Statistic stat = new Statistic(demandaProducto);
            double miu = stat.getMedia();
            double sigma = stat.sD();

            PModel.staticInstance(12.5, 12.5, 0.95, sigma, miu, null, prod.getcL());

            ddlbl.setText("---");
            calbl.setText("---");
            noalbl.setText("---");
            qoptlbl.setText("---");
            roplbl.setText(PModel.getROP().toString());
            avgivlbl.setText("---");
            maxivlbl.setText("---");

            drawLine();
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            Alerts.exceptionAlert(e.getMessage(), sw.toString());
        }
    }

    private void drawLine(){
        qLineChart.getData().clear();
    }

    private void drawLine(double istock, double rop, double qopt, double qm, double t){
        qLineChart.getData().clear();
        XYChart.Series is = new XYChart.Series();
        is.getData().add(new XYChart.Data<>(0, istock));
        is.getData().add(new XYChart.Data<>(250, istock));
        is.setName("Stock de seguridad");

        XYChart.Series rorder = new XYChart.Series();
        rorder.getData().add(new XYChart.Data<>(0, rop));
        rorder.getData().add(new XYChart.Data<>(250, rop));
        rorder.setName("Punto de reorden");

        XYChart.Series qoptim = new XYChart.Series();
        qoptim.getData().add(new XYChart.Data<>(0, qopt));
        qoptim.getData().add(new XYChart.Data<>(250, qopt));
        qoptim.setName("Q Óptimo");

        XYChart.Series qmax = new XYChart.Series();
        qmax.getData().add(new XYChart.Data<>(0, qm));
        qmax.getData().add(new XYChart.Data<>(250, qm));
        qmax.setName("Q máximo");

        XYChart.Series maingraf = new XYChart.Series();

        int ct = 0;
        while (ct <= 100) {
            maingraf.getData().add(new XYChart.Data<>(ct, qm));
            ct+=t;
            maingraf.getData().add(new XYChart.Data<>(ct, istock));
        }
        maingraf.setName("Línea principal");

        yAxis.setUpperBound(qm + 2);
        yAxis.setTickUnit(qm / 5);

        qLineChart.getData().addAll(is, rorder, qoptim, qmax, maingraf);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(0);
        xAxis.setUpperBound(100);
        xAxis.setTickUnit(10);
        xAxis.setLabel("Tiempo (días)");

        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(0);
        yAxis.setUpperBound(50);
        yAxis.setTickUnit(5);
        yAxis.setLabel("Unidades");
        //cargar productos en el combobox
        try {
            ProductoIDAO pidao = new ProductoIDAO();
            List<ProductoEntity> productos = pidao.getSpecifyProduct("Independiente");
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
