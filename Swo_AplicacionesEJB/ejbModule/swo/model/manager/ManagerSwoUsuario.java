package swo.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import swo.model.entities.SwoUsuario;

/**
 * Session Bean implementation class ManagerSwoUsuarios
 */
@Stateless
@LocalBean
public class ManagerSwoUsuario {
	@PersistenceContext(unitName = "usuariosDS")
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public ManagerSwoUsuario() {
        
    }

    public List<SwoUsuario> findAllSwoUsuarios(){
    	String consulta="SELECT s FROM SwoUsuario s  order by s.apellidoUsu";
    	Query q=em.createQuery(consulta, SwoUsuario.class);
    	return q.getResultList();
    }
    
    public SwoUsuario findSwoUsuarioByCodigoUsu(Integer codigoUsu) {
    	return em.find(SwoUsuario.class, codigoUsu);
    }
    
    public void insertarSwoUsuario(SwoUsuario swoUsuario) {
    	em.persist(swoUsuario);   			
    }
    
    public void eliminarSwoUsuario(Integer codigoUsu) {
    	SwoUsuario swoUsuario=findSwoUsuarioByCodigoUsu(codigoUsu);
    	if(swoUsuario!=null)
    		em.remove(swoUsuario);
    }
    
    public void actualizarSwoUsuario( SwoUsuario swoUsuario) throws Exception{
    	SwoUsuario s= findSwoUsuarioByCodigoUsu(swoUsuario.getCodigoUsu());
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
