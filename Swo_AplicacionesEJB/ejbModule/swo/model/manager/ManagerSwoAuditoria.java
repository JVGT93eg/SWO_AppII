package swo.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import swo.model.entities.SwoEvento;

/**
 * Session Bean implementation class ManagerSwoAuditoria
 */
@Stateless
@LocalBean
public class ManagerSwoAuditoria {
	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public ManagerSwoAuditoria() {
        // TODO Auto-generated constructor stub
    }
    
    public List<SwoEvento> findAllSwoEventos(){
    	String consulta="SELECT s FROM SwoEvento s";
    	Query q=em.createQuery(consulta, SwoEvento.class);
    	return q. getResultList();
    }

}
