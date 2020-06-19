package inv.mgr.views;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;

public class DemandController extends FXController{
    @FXML
    private JFXButton backBtn;

    public void handleBack(){
        this.mainApp.showHome();
    }
}
