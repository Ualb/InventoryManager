package inv.mgr.views;

import inv.mgr.model.dao.impl.DemandaIDAO;
import inv.mgr.model.entities.DemandaEntity;
import inv.mgr.model.entities.ProductoEntity;
import inv.mgr.utils.productioncalcs.MRP;
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

public class MRPSingleController extends MRPTableController implements Initializable {

    public TableColumn<RowExplosionMainTable, Integer> weekOrMoth;
    public TableColumn<RowExplosionMainTable, Integer> demand;
    public TableColumn<RowExplosionMainTable, Integer> production;
    public TableColumn<RowExplosionMainTable, Integer> endInventory;
    public TableColumn<RowExplosionMainTable, Double> H;
    public TableColumn<RowExplosionMainTable, Double> S;
    public TableColumn<RowExplosionMainTable, Double> CT;
    @FXML
    private TableView<RowExplosionMainTable> mainTable;

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

        if (ConstansDatas.typeMethod.equals("L4L")) {
            try {
                List<RowExplosionMainTable> list = insertDemands(true);
                list = MRP.getL4L(list);

                ObservableList<RowExplosionMainTable> observableList = FXCollections.observableArrayList();
                observableList.setAll(list);
                mainTable.setItems(observableList);
            } catch (Exception e) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                Alerts.exceptionAlert(e.getMessage(), sw.toString());
            }
        } else {
            try {
                List<RowExplosionMainTable> list = insertDemands(false);
                System.out.println(list);
                list = MRP.getEOQ(list);

                ObservableList<RowExplosionMainTable> observableList = FXCollections.observableArrayList();
                observableList.setAll(list);
                mainTable.setItems(observableList);
            } catch (Exception e) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                Alerts.exceptionAlert(e.getMessage(), sw.toString());
            }
        }
    }

    private List<RowExplosionMainTable> insertDemands(boolean isL4L) throws Exception {
        ProductoEntity product = ConstansDatas.productSelectedByDependenceType;
        DemandaIDAO demandAccess = new DemandaIDAO();
        List<DemandaEntity> demandList = demandAccess.allDebamandByID(product.getProductoId());
        List<RowExplosionMainTable> list = new ArrayList<>();

        for (DemandaEntity demand: demandList) {
            if (isL4L) {
                list.add(new RowExplosionMainTable((int) demand.getcTiempo()
                        , demand.getCantidad()
                        , product.getcS()
                        , false));
            } else {
                list.add(new RowExplosionMainTable((int) demand.getcTiempo()
                        , demand.getCantidad()
                        , product.getcS()
                        , false
                        , product.getcH()));
            }
        }
        Collections.sort(list, Comparator.comparing(RowExplosionMainTable::getWeekOrMonth));

        return list;
    }


}
