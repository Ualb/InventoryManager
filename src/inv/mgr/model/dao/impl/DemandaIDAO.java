package inv.mgr.model.dao.impl;

import inv.mgr.model.dao.DemandaDAO;
import inv.mgr.model.entities.DemandaEntity;

public class DemandaIDAO extends GenericIDAO<DemandaEntity, Integer> implements DemandaDAO {
    public DemandaIDAO() throws Exception {
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
