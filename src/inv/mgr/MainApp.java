package inv.mgr;

import inv.mgr.views.FXController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import inv.mgr.utils.viewsutils.Alerts;

import java.io.PrintWriter;
import java.io.StringWriter;

public class MainApp extends Application {

    Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setMaximized(true);
        primaryStage.setTitle("Inventory Manager");
        showHome();
    }

    /**
     *Mostrar ventana principal
     */
    public void showHome(){
        refreshPrimaryStage("views/fxml/Home.fxml");
    }

    /**
     * Mostrar ventana de la demanda
     */
    public void showDemand(){
        refreshPrimaryStage("views/fxml/Demand.fxml");
    }

    /**
     * Refrescar escena principal
     */
    private void refreshPrimaryStage(String view_path){
        // Se crea un fxml loader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource(view_path));
        // Se intenta cargar
        try {
            AnchorPane root = loader.load();
            FXController ctrl = loader.getController();
            ctrl.setMainApp(this);
            primaryStage.setMaximized(false);
            primaryStage.setScene(new Scene(root));
            primaryStage.setMaximized(true);
            primaryStage.show();
        } catch (Exception e) {
            //Forma de imprimir excepciones usando el alert
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            Alerts.exceptionAlert(e.getMessage(), sw.toString());
        }
    }
}
