package inv.mgr.views;

import inv.mgr.model.entities.ProductoEntity;

/**
 * Clase para controladores de tablas MRP, su principal parámetro es el producto7
 * que funciona como principal
 */
public class MRPTableController extends FXController{
    private ProductoEntity producto;

    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }
}
