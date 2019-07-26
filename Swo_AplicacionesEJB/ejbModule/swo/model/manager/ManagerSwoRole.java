package swo.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import swo.model.entities.SwoRole;

/**
 * Session Bean implementation class ManagerSwoRole
 */
@Stateless
@LocalBean
public class ManagerSwoRole {
	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public ManagerSwoRole() {
        // TODO Auto-generated constructor stub
    }
    
    public List<SwoRole> findAllSwoRoles(){
    	String consulta="SELECT s FROM SwoRole s  order by s.nombreRol";
    	Query q=em.createQuery(consulta, SwoRole.class);
    	return q.getResultList();
    }

    public SwoRole findSwoRoleByCodigoRol(Integer codigoRol) {
    	return em.find(SwoRole.class, codigoRol);
    }

    public void insertarSwoRole(SwoRole swoRole) {
    	em.persist(swoRole);   			
    }

    public void eliminarSwoRole(Integer codigoRol) {
    	SwoRole swoRole=findSwoRoleByCodigoRol(codigoRol);
    	if(swoRole!=null)
    		em.remove(swoRole);
    }

    public void actualizarSwoRole( SwoRole swoRole) throws Exception{
    	SwoRole s= findSwoRoleByCodigoRol(swoRole.getCodigoRol());
    	if(s==null)
    		throw new Exception("No existe el rol con el código especificado");
    	s.setNombreRol(swoRole.getNombreRol());
    	s.setDescripcionRol(swoRole.getDescripcionRol());
    	em.merge(s);
    }

}
