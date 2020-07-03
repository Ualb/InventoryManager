package inv.mgr.views;

import inv.mgr.model.dao.impl.ProductoIDAO;
import inv.mgr.model.entities.ProductoEntity;
import inv.mgr.utils.viewsutils.Alerts;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class ProductListController extends FXController implements Initializable {

    @FXML
    private TableView<ProductoEntity> mainTable;

    @FXML
    private TableColumn<ProductoEntity, String> nombreCol;

    @FXML
    private TableColumn<ProductoEntity, Double> costoCol;

    @FXML
    private TableColumn<ProductoEntity, Double> secstockCol;

    @FXML
    private TableColumn<ProductoEntity, Double> chCol;

    @FXML
    private TableColumn<ProductoEntity, Double> clCol;

    @FXML
    private TableColumn<ProductoEntity, Double> csCol;

    @FXML
    private TableColumn<ProductoEntity, String> demtyoeCol;

    @FXML
    private TableColumn<ProductoEntity, String> prodtypeCol;


    public void handleBack(){this.mainApp.showHome();}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nombreCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getNombre()));
        costoCol.setCellValueFactory(cellData->new SimpleDoubleProperty(cellData.getValue().getCosto()).asObject());
        csCol.setCellValueFactory(cellData->new SimpleDoubleProperty(cellData.getValue().getcS()).asObject());
        clCol.setCellValueFactory(cellData->new SimpleDoubleProperty(cellData.getValue().getcL()).asObject());
        chCol.setCellValueFactory(cellData->new SimpleDoubleProperty(cellData.getValue().getcH()).asObject());
        secstockCol.setCellValueFactory(cellData->new SimpleDoubleProperty(cellData.getValue().getReserva()).asObject());
        demtyoeCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTipoDemanda()));
        prodtypeCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTipoProducto()));

        try {
            ProductoIDAO pidao = new ProductoIDAO();
            List<ProductoEntity> productos = pidao.findAll();
            mainTable.setItems(FXCollections.observableArrayList(productos));
        } catch (Exception e) {
            //Forma de imprimir excepciones usando el alert
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            Alerts.exceptionAlert(e.getMessage(), sw.toString());
        }

    }
}
