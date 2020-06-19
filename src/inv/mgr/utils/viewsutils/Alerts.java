package utils.viewsutils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.util.Optional;

public class Alerts {

    /**
    * Método para mostrar un mensaje simple (error, información o advertencia)
    * @param msg Mensaje de error
    * @param type tipo de alerta (1 información, 2 advertencia, 4 error)
    * */
    public static void simpleAlert(String msg, int type){
        if(type == 3 || type > 4){
            throw new IllegalArgumentException("Solo alertas simples");
        }
        Alert alert = new Alert(Alert.AlertType.values()[type]);
        alert.setTitle("InventoryManager");
        String header = (type == 1)? "Información" : (type == 2)? "Advertencia" : "Error";
        alert.setHeaderText(header);
        alert.setContentText(msg);

        alert.showAndWait();
    }

    /**
     * Método para mostrar un mensaje
     * @param exMsg Mensaje de excepción
     * @param exStrace stacktrace de la excepción
     * */
    public static void exceptionAlert(String exMsg, String exStrace){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("InventoryManager");
        alert.setHeaderText("EXCEPCIÓN");
        alert.setContentText(exMsg);

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exStrace);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }

    /**
     * Método para mostrar un dialogo de confirmación
     * @param msg mensaje a mostrar
     * */
    public static boolean confirmDialog(String msg){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("InventoryManager");
        alert.setHeaderText("Confirmación");
        alert.setContentText(msg);

        Optional<ButtonType> result = alert.showAndWait();
        return result.filter(buttonType -> buttonType == ButtonType.OK).isPresent();
    }

}
