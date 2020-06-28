package inv.mgr.views;

import inv.mgr.model.entities.ProductoEntity;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class MRPMultipleController extends MRPTableController implements Initializable {
    @FXML
    private TableView<?> mainTable;

    @FXML
    private TableView<?> auxTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
