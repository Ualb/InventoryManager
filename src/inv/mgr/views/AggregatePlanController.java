package inv.mgr.views;

import com.jfoenix.controls.JFXTextField;
import inv.mgr.utils.viewsutils.JFXValidators;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class AggregatePlanController extends FXController implements Initializable {

    @FXML
    private TableView<?> mainTable;

    @FXML
    private JFXTextField outsourcingTxt;

    @FXML
    private JFXTextField contratTxt;

    @FXML
    private JFXTextField despidoTxt;

    @FXML
    private JFXTextField moextraTxt;

    @FXML
    private JFXTextField secstockTxt;

    @FXML
    private JFXTextField timeTxt;

    @FXML
    private JFXTextField monormalTxt;

    @FXML
    private JFXTextField moinicialTxt;

    @FXML
    private JFXTextField faltanteTxt;

    public void handleBack(){
        this.mainApp.showHome();
    }

//    TODO agregar combo box para seleccionar la estrategia: Persecucion, Fuerza Nivelada, Horas Extras, Outsourcing

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        JFXValidators.addNumberValidator(faltanteTxt);
        JFXValidators.addNumberValidator(contratTxt);
        JFXValidators.addNumberValidator(moinicialTxt);
        JFXValidators.addNumberValidator(moextraTxt);
        JFXValidators.addNumberValidator(despidoTxt);
        JFXValidators.addNumberValidator(monormalTxt);
        JFXValidators.addNumberValidator(outsourcingTxt);
        JFXValidators.addNumberValidator(secstockTxt);
        JFXValidators.addNumberValidator(timeTxt);
    }
}
