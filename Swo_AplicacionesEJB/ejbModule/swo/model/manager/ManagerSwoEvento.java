package swo.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import swo.model.entities.SwoEvento;

/**
 * Session Bean implementation class ManagerSwoEvento
 */
@Stateless
@LocalBean
public class ManagerSwoEvento {
    @PersistenceContext
    private EntityManager em;
    
    public ManagerSwoEvento() {
        
    }
    
    public List<SwoEvento> findAllSwoEventos(){
    	String consulta="SELECT s FROM SwoEvento s";
    	Query q=em.createQuery(consulta, SwoEvento.class);
    	return q. getResultList();
    }

    public SwoEvento findSwoEventoByCodigoEve(Integer codigoEve) {
    	return em.find(SwoEvento.class, codigoEve);
    }
    
    public void insertarSwoEvento(SwoEvento swoEvento) {
    	em.persist(swoEvento);   			
    }
    
}
