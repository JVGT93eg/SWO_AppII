package swo.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import swo.model.entities.SwoRole;
import swo.model.entities.SwoUsuario;

/**
 * Session Bean implementation class ManagerSwoUsuario
 */
@Stateless
@LocalBean
public class ManagerSwoUsuario {
	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public ManagerSwoUsuario() {
        // TODO Auto-generated constructor stub
    }
    
    public List<SwoUsuario> findAllSwoUsuarios(){
    	String consulta="SELECT s FROM SwoUsuario s  order by s.apellidoUsu";
    	Query q=em.createQuery(consulta, SwoUsuario.class);
    	return q.getResultList();
    }
    
    public SwoUsuario findSwoUsuarioByCedulaUsu(String cedulaUsu) {
    	return em.find(SwoUsuario.class, cedulaUsu);
    }
    
    public void insertarSwoUsuario(SwoUsuario swoUsuario, Integer codigoRol) {
    	SwoRole swoRoles=em.find(SwoRole.class, codigoRol);
    	swoUsuario.setSwoRole(swoRoles);
    	em.persist(swoUsuario);   			
    }
        
    public void eliminarSwoUsuario(String cedulaUsu) {
    	SwoUsuario swoUsuario=findSwoUsuarioByCedulaUsu(cedulaUsu);
    	if(swoUsuario!=null)
    		em.remove(swoUsuario);
    }
    
    public void actualizarSwoUsuario( SwoUsuario swoUsuario) throws Exception{
    	SwoUsuario s= findSwoUsuarioByCedulaUsu(swoUsuario.getCedulaUsu());
    	if(s==null)
    		throw new Exception("No existe el usuario con el código especificado");
    	s.setCedulaUsu(swoUsuario.getCedulaUsu());
    	s.setNombreUsu(swoUsuario.getNombreUsu());
    	s.setApellidoUsu(swoUsuario.getApellidoUsu());
    	s.setClaveUsu(swoUsuario.getClaveUsu());
    	s.setEdadUsu(swoUsuario.getEdadUsu());
    	s.setTelefonoUsu(swoUsuario.getTelefonoUsu());
    	s.setEmailUsu(swoUsuario.getEmailUsu());
    	s.setDireccionUsu(swoUsuario.getDireccionUsu());
    	s.setFechaNaciUsu(swoUsuario.getFechaNaciUsu());
    	em.merge(s);
    }

}
