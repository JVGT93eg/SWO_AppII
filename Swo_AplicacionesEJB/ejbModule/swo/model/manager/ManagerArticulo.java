package swo.model.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import swo.model.entities.SwoArticulo;
import swo.model.entities.SwoTratamiento;

@Stateless
@LocalBean
public class ManagerArticulo {

@PersistenceContext
private EntityManager em;
@EJB
private ManagerDAO managerDAO;
	
	public ManagerArticulo() {
 	
	}
	
	public List<SwoArticulo> listarArticulo(){
		String sentencia = "SELECT s FROM SwoArticulo s";
		Query q = em.createQuery(sentencia,SwoArticulo.class);
		return q.getResultList();
	}
	   public SwoArticulo buscarporCodigo(int codigoArt){
	        return em.find(SwoArticulo.class, codigoArt);
	    }
	     
	   
	   public void insertarArticulo(String nombreArt, String descripcionArt, Date fechaExpiArt, Date  fechaFabrArt, String marcaArt,
			   						String riesgoArt, String loteArt, int maxArt, int minArt, int stockArt) {
	    	SwoArticulo articulo= new SwoArticulo();
	    	articulo.setNombreArt(nombreArt);
	    	articulo.setDescripcionArt(descripcionArt);
	    	articulo.setFechaExpiArt(fechaExpiArt);
	    	articulo.setFechaFabrArt(fechaFabrArt);
	    	articulo.setMarcaArt(marcaArt);
	    	articulo.setRiesgoArt(riesgoArt);
	    	articulo.setLoteArt(loteArt);
	    	articulo.setMaxArt(maxArt);
	    	articulo.setMinArt(minArt);
	    	articulo.setStockArt(stockArt);
	    	em.persist(articulo);
	    }
	   
	  
	    
	    public void eliminarArticulo(int codigoArt) {
	    	SwoArticulo articulo= buscarporCodigo(codigoArt);
	    	if(articulo!=null)
	    		em.remove(articulo);
	    }
	    
	    public void actualizarArticulo(SwoArticulo articulo) throws Exception {
	    	SwoArticulo e = buscarporCodigo(articulo.getCodigoArt());
	    	if(e==null)
	    		throw new Exception("No existe el articulo especificado.");
	    	e.setNombreArt(articulo.getNombreArt());
	    	e.setDescripcionArt(articulo.getDescripcionArt());
	    	e.setFechaExpiArt(articulo.getFechaExpiArt());
	    	e.setFechaFabrArt(articulo.getFechaFabrArt());
	    	e.setMarcaArt(articulo.getMarcaArt());
	    	e.setRiesgoArt(articulo.getRiesgoArt());
	    	e.setLoteArt(articulo.getLoteArt());
	    	e.setMaxArt(articulo.getMaxArt());
	    	e.setMinArt(articulo.getMinArt());
	    	e.setStockArt(articulo.getStockArt());
	    	em.merge(e);	
	    	
	    }
	   
	  	
	  	@SuppressWarnings("unchecked")
		public List<SwoArticulo> findAll_Articulos(){
	  		return managerDAO.findAll(SwoArticulo.class, "o.nombreArt");
	  	}
	  	
	  	 
	    

}
