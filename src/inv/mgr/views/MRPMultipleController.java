package inv.mgr.views;

import inv.mgr.model.dao.impl.DemandaIDAO;
import inv.mgr.model.entities.DemandaEntity;
import inv.mgr.model.entities.ProductoEntity;
import inv.mgr.utils.productioncalcs.MRP;
import inv.mgr.utils.productionutils.ExplosionTable;
import inv.mgr.utils.productionutils.RowExplosionAuxiliarTable;
import inv.mgr.utils.productionutils.RowExplosionMainTable;
import inv.mgr.utils.viewsutils.Alerts;
import inv.mgr.utils.viewsutils.ConstansDatas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.*;

public class MRPMultipleController extends MRPTableController implements Initializable {
    public TableColumn<RowExplosionMainTable, Integer> weekOrMoth;
    public TableColumn<RowExplosionMainTable, Integer> demand;
    public TableColumn<RowExplosionMainTable, Integer> production;
    public TableColumn<RowExplosionMainTable, Integer> endInventory;
    public TableColumn<RowExplosionMainTable, Double> H;
    public TableColumn<RowExplosionMainTable, Double> S;
    public TableColumn<RowExplosionMainTable, Double> CT;

    public TableColumn<RowExplosionAuxiliarTable, String> rang;
    public TableColumn<RowExplosionAuxiliarTable, Integer> lot;
    public TableColumn<RowExplosionAuxiliarTable, Double> h;
    public TableColumn<RowExplosionAuxiliarTable, Double> s;
    public TableColumn<RowExplosionAuxiliarTable, Double> CT2;
    public TableColumn<RowExplosionAuxiliarTable, Double> CU;
    @FXML
    private TableView<RowExplosionMainTable> mainTable;

    @FXML
    private TableView<RowExplosionAuxiliarTable> auxTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        weekOrMoth.setCellValueFactory(new PropertyValueFactory<>("weekOrMonth"));
        demand.setCellValueFactory(new PropertyValueFactory<>("demand"));
        production.setCellValueFactory(new PropertyValueFactory<>("production"));
        endInventory.setCellValueFactory(new PropertyValueFactory<>("endInventory"));
        H.setCellValueFactory(new PropertyValueFactory<>("H"));
        S.setCellValueFactory(new PropertyValueFactory<>("S"));
        CT.setCellValueFactory(new PropertyValueFactory<>("CT"));
        mainTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        rang.setCellValueFactory(new PropertyValueFactory<>("rang"));
        lot.setCellValueFactory(new PropertyValueFactory<>("lot"));
        h.setCellValueFactory(new PropertyValueFactory<>("h"));
        s.setCellValueFactory(new PropertyValueFactory<>("s"));
        CT2.setCellValueFactory(new PropertyValueFactory<>("CT2"));
        auxTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        if (ConstansDatas.typeMethod.equals("LTC")) {
            try {
                ExplosionTable explosionTable =insertDemand();
                explosionTable = MRP.getLTC(explosionTable.getMainTable(), explosionTable.getAuxiliarTable());

                ObservableList<RowExplosionMainTable> observableList = FXCollections.observableArrayList();
                observableList.setAll(explosionTable.getMainTable());
                mainTable.setItems(observableList);

                ObservableList<RowExplosionAuxiliarTable> observableList2 = FXCollections.observableArrayList();
                observableList2.setAll(explosionTable.getAuxiliarTable());
                auxTable.setItems(observableList2);
            } catch (Exception e) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                Alerts.exceptionAlert(e.getMessage(), sw.toString());
            }
        } else {
            try {
                ExplosionTable explosionTable = insertDemand();
                explosionTable = MRP.getLUC(explosionTable.getMainTable(), explosionTable.getAuxiliarTable());

                ObservableList<RowExplosionMainTable> observableList = FXCollections.observableArrayList();
                observableList.setAll(explosionTable.getMainTable());
                mainTable.setItems(observableList);

                ObservableList<RowExplosionAuxiliarTable> observableList2 = FXCollections.observableArrayList();
                observableList2.setAll(explosionTable.getAuxiliarTable());
                auxTable.setItems(observableList2);
            } catch (Exception e) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                Alerts.exceptionAlert(e.getMessage(), sw.toString());
            }
        }
    }

    private ExplosionTable insertDemand() throws Exception {
        ProductoEntity product = ConstansDatas.productSelectedByDependenceType;
        DemandaIDAO demandAccess = new DemandaIDAO();
        List<DemandaEntity> demandList = demandAccess.allDebamandByID(product.getProductoId());
        List<RowExplosionMainTable> list = new ArrayList<>();
        List<RowExplosionAuxiliarTable> auxList = new ArrayList<>();

        int l = 1;
        String rangString = "1";
        for (DemandaEntity demand: demandList) {
            list.add(new RowExplosionMainTable((int) demand.getcTiempo()
                    , demand.getCantidad()
                    , product.getcS()
                    , true
                    , product.getcH()));
            auxList.add(new RowExplosionAuxiliarTable(rangString
                    , 0, 0.0, 0.0, 0.0, 0.0));
            ++l;
            rangString += "-" + l;
        }

        Collections.sort(list, Comparator.comparing(RowExplosionMainTable::getWeekOrMonth));
        return new ExplosionTable(auxList, list);
    }
}
