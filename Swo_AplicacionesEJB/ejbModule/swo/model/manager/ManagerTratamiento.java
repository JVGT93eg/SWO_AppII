package swo.model.manager;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
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
	@EJB
	private ManagerDAO managerDAO;
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
	    
	    public void actualizarTratamiento(SwoTratamiento tratamiento, int cod_Categoria) throws Exception {
	    	SwoTratamiento e = buscarporCodigo(tratamiento.getCodigoTra());
	    	if(e==null)
	    		throw new Exception("No existe el tratamiento especificada.");
	    	e.setDescripcionTra(tratamiento.getDescripcionTra());
	    	e.setEstadoTra(tratamiento.getEstadoTra());
	    	SwoCategoria cat=em.find(SwoCategoria.class, cod_Categoria);
	    	e.setSwoCategoria(cat);
	    	e.setPrecioTra(tratamiento.getPrecioTra());
	    	em.merge(e);
	    	
	    }
	    
	    public SwoTratamiento findTratamientoById(Integer codigotrata) throws Exception{
	  		return (SwoTratamiento) managerDAO.findById(SwoTratamiento.class, codigotrata);
	  	} 
	    
	    
		/**
	  	 * Metodo finder para consulta de Tratamientos.
	  	 * Hace uso del componente {@link swo.model.manager.ManagerDAO ManagerDAO} de la capa model.
	  	 * @return listado de Tratamientos ordenados por descripción.
	  	 */
	  	@SuppressWarnings("unchecked")
	  	public List<SwoTratamiento> findAll_Tratamiento(){
	  		return managerDAO.findAll(SwoTratamiento.class, "o.descripcionTra");
	  	}
	  	
//	  	public int obtenerExistenciaTra(Integer codigotrata) throws Exception{
//	  		SwoTratamiento t;
//	  		t=findTratamientoById(codigotrata);
//	  		return t.getExistencia().intValue();
//	  	}

}
