package inv.mgr.utils.viewsutils.stringconverters;

import inv.mgr.model.entities.ProductoEntity;
import javafx.util.StringConverter;

public class ProductoConverter extends StringConverter<ProductoEntity> {

    /**
     * Converts the object provided into its string form.
     * Format of the returned string is defined by the specific converter.
     *
     * @param object
     * @return a string representation of the object passed in.
     */
    @Override
    public String toString(ProductoEntity object) {
        return object.getNombre();
    }

    /**
     * Converts the string provided into an object defined by the specific converter.
     * Format of the string and type of the resulting object is defined by the specific converter.
     *
     * @param string
     * @return an object representation of the string passed in.
     */
    @Override
    public ProductoEntity fromString(String string) {
        return null;
    }
}
