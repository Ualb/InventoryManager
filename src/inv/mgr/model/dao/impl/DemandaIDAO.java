package inv.mgr.model.dao.impl;

import inv.mgr.model.dao.DemandaDAO;
import inv.mgr.model.entities.DemandaEntity;
import inv.mgr.utils.viewsutils.Alerts;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class DemandaIDAO extends GenericIDAO<DemandaEntity, Integer> implements DemandaDAO {
    public DemandaIDAO() throws Exception {
    }

    public List<DemandaEntity> allDebamandByID(int id) {
        Session session = sessionFactory.getCurrentSession();
        List<DemandaEntity> demandaEntities = new ArrayList<>();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from DemandaEntity as de where de.productoId = 6 " +
                            "and de.tDisponible = null"
                    , DemandaEntity.class);
            demandaEntities = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            Alerts.simpleAlert("No se puede acceder a la lista de demandas", 4);
        }
        return demandaEntities;
    }

    public List<DemandaEntity> allDebamandByIDPlain(int id) {
        Session session = sessionFactory.getCurrentSession();
        List<DemandaEntity> demandaEntities = new ArrayList<>();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from DemandaEntity as de where de.productoId = 6 " +
                            "and de.tDisponible is not null"
                    , DemandaEntity.class);
            demandaEntities = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            Alerts.simpleAlert("No se puede acceder a la lista de demandas", 4);
        }
        return demandaEntities;
    }

    /*    public List<DemandaEntity> getDemand(Integer producto_id) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery cq = (CriteriaQuery) cb.createQuery(DemandaEntity.class);
            cq.get


            session.getTransaction().commit();

            return entity;
        } catch (ConstraintViolationException cve) {
            try {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
            } catch (Exception exc) {
                LOGGER.log(Level.WARNING, "Falló al hacer un rollback", exc);
            }
            throw new Exception(cve);
        } catch (RuntimeException ex) {
            try {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
            } catch (Exception exc) {
                LOGGER.log(Level.WARNING, "Falló al hacer un rollback", exc);
            }
            throw ex;
        } catch (Exception ex) {
            try {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
            } catch (Exception exc) {
                LOGGER.log(Level.WARNING, "Falló al hacer un rollback", exc);
            }
            throw new RuntimeException(ex);
        }
    }*/
}
