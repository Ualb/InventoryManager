package inv.mgr.views;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import inv.mgr.model.dao.impl.DemandaIDAO;
import inv.mgr.model.dao.impl.ProductoIDAO;
import inv.mgr.model.entities.DemandaEntity;
import inv.mgr.model.entities.ProductoEntity;
import inv.mgr.utils.productioncalcs.AggregatePlanning;
import inv.mgr.utils.productionutils.MonthAggregatePlanning;
import inv.mgr.utils.productionutils.PlainAggregatePlanning;
import inv.mgr.utils.viewsutils.Alerts;
import inv.mgr.utils.viewsutils.JFXValidators;
import inv.mgr.utils.viewsutils.stringconverters.ProductoConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.*;

public class AggregatePlanController extends FXController implements Initializable {

    public JFXComboBox methodCmb;
    public TableColumn<MonthAggregatePlanning, Integer> number;
    public TableColumn<MonthAggregatePlanning, Integer> demand;
    public TableColumn<MonthAggregatePlanning, Integer> realProduction;
    public TableColumn<MonthAggregatePlanning, Integer> startInventory;
    public TableColumn<MonthAggregatePlanning, Integer> endInventory;
    public TableColumn<MonthAggregatePlanning, Integer> hoursRequered;
    public TableColumn<MonthAggregatePlanning, Integer> hoursAvaileble;
    public TableColumn<MonthAggregatePlanning, Integer> numberOfEmployeesRequered;
    public TableColumn<MonthAggregatePlanning, Double> cstMissing;
    public TableColumn<MonthAggregatePlanning, Double>  cstRecruitment;
    public TableColumn<MonthAggregatePlanning, Double>  cstDismissal;
    public TableColumn<MonthAggregatePlanning, Double>  cstOutsourcing;
    public TableColumn<MonthAggregatePlanning, Double>  cstExtraHour;
    public TableColumn<MonthAggregatePlanning, Double>  cstNormal;
    public TableColumn<MonthAggregatePlanning, Double>  cstH;
    public TableColumn<MonthAggregatePlanning, Double>  cstMaterials;
    public TableColumn<MonthAggregatePlanning, Double>  cstTotal;
    public JFXComboBox productCmb;
    public TableColumn<MonthAggregatePlanning, Integer> production;
    public TableColumn<MonthAggregatePlanning, Integer> productionOut;
    @FXML
    private TableView<MonthAggregatePlanning> mainTable;

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

        methodCmb.setItems(FXCollections.observableArrayList("Estrategia de persecución", "Fuerza Nivelada", "Fuerza Nivelada con Horas Extras", "Fuerza Nivelada con Outsourcing"));
        try {
            ProductoIDAO pidao = new ProductoIDAO();
            List<ProductoEntity> products = pidao.getSpecifyProduct("Dependiente");
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

        number.setCellValueFactory(new PropertyValueFactory<>("number"));
        demand.setCellValueFactory(new PropertyValueFactory<>("demand"));
        realProduction.setCellValueFactory(new PropertyValueFactory<>("realProduction"));
        production.setCellValueFactory(new PropertyValueFactory<>("productionRequered"));
        productionOut.setCellValueFactory(new PropertyValueFactory<>("productsWithOutsourcing"));
        startInventory.setCellValueFactory(new PropertyValueFactory<>("startInventory"));
        endInventory.setCellValueFactory(new PropertyValueFactory<>("endInventory"));
        hoursRequered.setCellValueFactory(new PropertyValueFactory<>("hoursRequered"));
        hoursAvaileble.setCellValueFactory(new PropertyValueFactory<>("hoursAvaileble"));
        numberOfEmployeesRequered.setCellValueFactory(new PropertyValueFactory<>("numberOfEmployeesRequered"));
        cstMissing.setCellValueFactory(new PropertyValueFactory<>("cstMissing"));
        cstRecruitment.setCellValueFactory(new PropertyValueFactory<>("cstRecruitment"));
        cstDismissal.setCellValueFactory(new PropertyValueFactory<>("cstDismissal"));
        cstOutsourcing.setCellValueFactory(new PropertyValueFactory<>("cstOutsourcing"));
        cstExtraHour.setCellValueFactory(new PropertyValueFactory<>("cstExtraHour"));
        cstNormal.setCellValueFactory(new PropertyValueFactory<>("cstNormal"));
        cstH.setCellValueFactory(new PropertyValueFactory<>("cstH"));
        cstMaterials.setCellValueFactory(new PropertyValueFactory<>("cstMaterials"));
        cstTotal.setCellValueFactory(new PropertyValueFactory<>("cstTotal"));

//        mainTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public void onShow(ActionEvent actionEvent) throws Exception {

        String strategyText = methodCmb.getValue().toString();
        ProductoEntity product = (ProductoEntity) productCmb.getValue();

        double cfText = Double.parseDouble(faltanteTxt.getText());
        double cstRecruitmentText = Double.parseDouble(contratTxt.getText());
        int moiText = Integer.parseInt(moinicialTxt.getText());
        double cstExtraHourText = Double.parseDouble(moextraTxt.getText());
        double cstDismissalText = Double.parseDouble(despidoTxt.getText());
        double cstNormalTimeText = Double.parseDouble(monormalTxt.getText());
        double cstOutsourcingText = Double.parseDouble(outsourcingTxt.getText());
        int stockSecurityText = Integer.parseInt(secstockTxt.getText());
        double normalHourTimeText = Double.parseDouble(timeTxt.getText());

        List<MonthAggregatePlanning> months = new ArrayList<>();
        List<DemandaEntity> demands = new DemandaIDAO().allDebamandByIDPlain(product.getProductoId());
        for (DemandaEntity dem: demands) {
            months.add(new MonthAggregatePlanning((int) dem.getcTiempo()
                                                    , dem.getCantidad()
                                                    , (int) dem.gettDisponible().doubleValue()));
        }
        Collections.sort(months, Comparator.comparing(MonthAggregatePlanning::getNumber));
        PlainAggregatePlanning plain = new PlainAggregatePlanning(product.getCosto()
                                                                    , product.getcH()
                                                                    , cfText
                                                                    , cstRecruitmentText
                                                                    , cstDismissalText
                                                                    , cstNormalTimeText
                                                                    , cstExtraHourText
                                                                    , (int) product.getReserva().doubleValue()
                                                                    , normalHourTimeText
                                                                    , 0.0
                                                                    , stockSecurityText
                                                                    , cstOutsourcingText
                                                                    , moiText
                                                                    , months
                                                                    , 0);

        switch (strategyText) {
            case "Estrategia de persecución":{
                plain = AggregatePlanning.getPersuitStrategy(plain);
                ObservableList<MonthAggregatePlanning> observableList = FXCollections.observableArrayList();
                observableList.setAll(plain.getList());
                mainTable.setItems(observableList);
                break;
            }
            case "Fuerza Nivelada":{
                plain = AggregatePlanning.getLevelForce(plain);
                ObservableList<MonthAggregatePlanning> observableList = FXCollections.observableArrayList();
                observableList.setAll(plain.getList());
                mainTable.setItems(observableList);
                break;
            }
            case "Fuerza Nivelada con Horas Extras":{
                plain = AggregatePlanning.getLevelForceWithOvertime(plain);
                ObservableList<MonthAggregatePlanning> observableList = FXCollections.observableArrayList();
                observableList.setAll(plain.getList());
                mainTable.setItems(observableList);
                break;
            }
            case "Fuerza Nivelada con Outsourcing": {
                plain = AggregatePlanning.getLevelForceWithOutsourcing(plain);
                ObservableList<MonthAggregatePlanning> observableList = FXCollections.observableArrayList();
                observableList.setAll(plain.getList());
                mainTable.setItems(observableList);
                break;
            }
        }

    }
}
