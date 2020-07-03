package inv.mgr.views;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;

public class HomeController extends FXController {
    @FXML
    private JFXButton demandaBtn;

    @FXML
    private JFXButton mrpBtn;

    @FXML
    private JFXButton addplanBtn;

    public void handleProductList(){this.mainApp.showProductList();}

    public void handleDemanda(){
        this.mainApp.showDemand();
    }

    public void handleMRP(){this.mainApp.showMRPRoot();}

    public void handleAP(){this.mainApp.showAggregatePlan();}
}
