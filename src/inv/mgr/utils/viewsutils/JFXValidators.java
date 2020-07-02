package inv.mgr.utils.viewsutils;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.scene.input.KeyEvent;


public class JFXValidators {
    /**
     * Agrega un validador de requerido a un campo de JFoenix
     * @param field: campo a agregar validador
     * @param msg: mensaje de error
     */
    public static void addRequiredValidator(JFXTextField field, String msg){
        RequiredFieldValidator val = new RequiredFieldValidator();
        field.getValidators().add(val);
        val.setMessage(msg);
        field.focusedProperty().addListener(((observable, oldValue, newValue) -> {
            if(!newValue){
                field.validate();
            }
        }));
    }

    /**
     * Agrega un validador numérico a un campo de texto
     * @param field
     */
    public static void addNumberValidator(JFXTextField field){
        NumberValidator val = new NumberValidator();
        field.getValidators().add(val);
        val.setMessage("Solo números");
        field.addEventFilter(KeyEvent.KEY_TYPED, (KeyEvent evt) -> {
            if(!field.validate()){
                evt.consume();
            }
        });
    }
}
