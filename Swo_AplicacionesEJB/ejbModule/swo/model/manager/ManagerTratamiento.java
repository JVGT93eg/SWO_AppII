package swo.model.manager;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import swo.model.entities.SwoCategoria;
import swo.model.entities.SwoTratamiento;

@Stateless
@LocalBean
public class ManagerTratamiento {
	
	@PersistenceContext
	private EntityManager em;
	
	public ManagerTratamiento() {
		
	}
	
	public List<SwoTratamiento> listarTratamiento(){
		String sentencia = "SELECT s FROM SwoTratamiento s";
		Query q = em.createQuery(sentencia,SwoTratamiento.class);
		return q.getResultList();
	}
	   public SwoTratamiento buscarporCodigo(int codigoTra){
	        return em.find(SwoTratamiento.class, codigoTra);
	    }
		public List<SwoCategoria> listarCategorias(){
			Query q;
			String sentencia = "SELECT s FROM SwoCategoria s";
			q = em.createQuery(sentencia,SwoCategoria.class);
			return q.getResultList();
		}
	
	  	   public SwoCategoria buscarPorCodigoC(int codCategoria) {
	  		   SwoCategoria categoria = em.find(SwoCategoria.class, codCategoria);
	  		   return categoria;
	   }
	   
	   
	   public void insertarTratamiento(String descripcionTra, String estadoTra, int codCategoria, double precioTra ) {
	    	SwoTratamiento tratamiento= new SwoTratamiento();
	    	SwoCategoria categoria = buscarPorCodigoC(codCategoria);
	    	tratamiento.setDescripcionTra(descripcionTra);
	    	tratamiento.setEstadoTra(estadoTra);
	    	tratamiento.setSwoCategoria(categoria);
	    	tratamiento.setPrecioTra(precioTra);
	    	em.persist(tratamiento);
	    }
	   
	  
	    
	    public void eliminarTratamiento(int codigoTra) {
	    	SwoTratamiento tratamiento= buscarporCodigo(codigoTra);
	    	if(tratamiento!=null)
	    		em.remove(tratamiento);
	    }
	    
	    public void actualizarTratamiento(SwoTratamiento tratamiento) throws Exception {
	    	SwoTratamiento e = buscarporCodigo(tratamiento.getCodigoTra());
	    	if(e==null)
	    		throw new Exception("No existe el tratamiento especificada.");
	    	e.setDescripcionTra(tratamiento.getDescripcionTra());
	    	e.setEstadoTra(tratamiento.getEstadoTra());
	    	e.setSwoCategoria(tratamiento.getSwoCategoria());
	    	e.setPrecioTra(tratamiento.getPrecioTra());
	    	em.merge(e);
	    	
	    }

}
