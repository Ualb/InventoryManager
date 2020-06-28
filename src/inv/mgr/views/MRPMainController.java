package inv.mgr.views;

import com.jfoenix.controls.JFXComboBox;
import inv.mgr.model.dao.impl.ProductoIDAO;
import inv.mgr.model.entities.ProductoEntity;
import inv.mgr.utils.viewsutils.Alerts;
import inv.mgr.utils.viewsutils.stringconverters.ProductoConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MRPMainController extends FXController implements Initializable {
    @FXML
    private BorderPane mainPane;

    @FXML
    private JFXComboBox<ProductoEntity> productCmb;

    @FXML
    private  JFXComboBox<String> methodCmb;


    public void handleBack(){this.mainApp.showHome();}
    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        methodCmb.setItems(FXCollections.observableArrayList("L4L", "EQQ", "LTC", "LUC"));
        try {
            ProductoIDAO pidao = new ProductoIDAO();
            List<ProductoEntity> products = pidao.findAll();
            ObservableList<ProductoEntity> prods = FXCollections.observableArrayList(products);
            productCmb.setItems(prods);
            productCmb.setConverter(new ProductoConverter());
        } catch (Exception e) {
            //Forma de imprimir excepciones usando el alert
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            Alerts.exceptionAlert(e.getMessage(), sw.toString());
        }
    }
}
