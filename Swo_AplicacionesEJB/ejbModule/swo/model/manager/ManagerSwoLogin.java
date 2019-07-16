package swo.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import swo.model.entities.SwoLogin;
import swo.model.entities.SwoRole;
import swo.model.entities.SwoUsuario;


/**
 * Session Bean implementation class ManagerLogin
 */
@Stateless
@LocalBean
public class ManagerSwoLogin {
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public ManagerSwoLogin() {
       
    }
    
    public List<SwoLogin> findAllSwoLogin(){
    	String consulta="SELECT s FROM SwoLogin s";
    	Query q=em.createQuery(consulta, SwoLogin.class);
    	return q.getResultList();
    }
    
    public SwoLogin findSwoLoginByCodigoLogin(Integer codigoLogin) {
    	return em.find(SwoLogin.class, codigoLogin);
    }
    
    public void insertarSwoLogin(SwoLogin swoLogin) {
    	em.persist(swoLogin);   			
    }
    
    public void eliminarSwoLogin(Integer codigoLogin) {
    	SwoLogin swoLogin=findSwoLoginByCodigoLogin(codigoLogin);
    	if(swoLogin!=null)
    		em.remove(swoLogin);
    }
    
//    public void actualizarSwoLogin(SwoLogin swoLogin, int codigoRol, int codigoUsu) throws Exception{
//    	SwoRole swoRole=em.find(SwoRole.class, codigoRol);
//    	SwoUsuario swoUsuario=em.find(SwoUsuario.class, codigoUsu);
//    	SwoLogin s=em.find(SwoLogin.class, swoLogin.getCodigoLogin());
//    	if(s==null)
//    		throw new Exception("No existe el tratamiento especificada.");
//    	s.setSwoRole(swoRole);
//    	s.setSwoUsuario(swoUsuario);
//    	em.merge(s);
//    }
    
    public void actualizarSwoLogin(SwoLogin swoLogin) throws Exception{
    	SwoLogin s= findSwoLoginByCodigoLogin(swoLogin.getCodigoLogin());
    	if(s==null)
    		throw new Exception("No existe un Login con el código especificado");
    	s.setSwoRole(swoLogin.getSwoRole());
    	s.setSwoUsuario(swoLogin.getSwoUsuario());
    	em.merge(s);
    }
     
    public List<SwoRole> findAllSwoRoles(){
    	String consulta="SELECT s FROM SwoRole s  order by s.nombreRol";
    	Query q=em.createQuery(consulta, SwoRole.class);
    	return q.getResultList();
    }
    
    public SwoRole findSwoRoleByCodigoRol(Integer codigoRol) {
    	return em.find(SwoRole.class, codigoRol);
    }
    
    public List<SwoUsuario> findAllSwoUsuarios(){
    	String consulta="SELECT s FROM SwoUsuario s  order by s.apellidoUsu";
    	Query q=em.createQuery(consulta, SwoUsuario.class);
    	return q.getResultList();
    }
    
    public SwoUsuario findSwoUsuarioByCodigoUsu(Integer codigoUsu) {
    	return em.find(SwoUsuario.class, codigoUsu);
    }
    
}
