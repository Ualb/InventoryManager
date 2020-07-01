package inv.mgr.model.dao.impl;

import inv.mgr.model.dao.ProductoDAO;
import inv.mgr.model.entities.ProductoEntity;
import inv.mgr.utils.viewsutils.Alerts;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ProductoIDAO extends GenericIDAO<ProductoEntity, Integer> implements ProductoDAO {
    public ProductoIDAO() throws Exception {
    }

    public List<ProductoEntity> getSpecifyProduct(String typeProduct) {
        Session session = sessionFactory.getCurrentSession();
        List<ProductoEntity> productList = new ArrayList<>();
        try {
            session.beginTransaction();

            Query query = session.createQuery("from ProductoEntity as pe where pe.tipoProducto = '"+ typeProduct +"'"
                    , ProductoEntity.class);
            productList = query.getResultList();

            session.getTransaction().commit();
        } catch (Exception e) {
           Alerts.simpleAlert("No se puede acceder a la lista de productos", 4);
        }
        return productList;
    }
}
