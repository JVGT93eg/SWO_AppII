package swo.model.manager;

import java.util.List;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import swo.model.entities.SwoCategoria;



@Stateless
@LocalBean
public class ManagerCategorias {
	@PersistenceContext
	private EntityManager em;
	
	public ManagerCategorias() {
		
	}
	
	public List<SwoCategoria> listarCategorias(){
		Query q;
		String sentencia = "SELECT s FROM SwoCategoria s";
		q = em.createQuery(sentencia,SwoCategoria.class);
		return q.getResultList();
	}
	
	   public SwoCategoria buscarporCodigo(int codCategoria){
	        return em.find(SwoCategoria.class, codCategoria);
	    }
	   
	   public void insertarCategoria(String nombreCat, String descripcionCat) {
	    	SwoCategoria categoria = new SwoCategoria();
	    	categoria.setNombreCat(nombreCat);
	    	categoria.setDescripcionCat(descripcionCat);
	    	em.persist(categoria);
	    }
	    
	    public void eliminarCategoria(int codCategoria) {
	    	SwoCategoria categoria = buscarporCodigo(codCategoria);
	    	if(categoria!=null)
	    		em.remove(categoria);
	    }
	    
	    public void actualizarCategoria(SwoCategoria categoria) throws Exception {
	    	SwoCategoria e = buscarporCodigo(categoria.getCodigoCat());
	    	if(e==null)
	    		throw new Exception("No existe la categoria especificada.");
	    	e.setNombreCat(categoria.getNombreCat());
	    	e.setDescripcionCat(categoria.getDescripcionCat());
	    	em.merge(e);
	    	
	    }
}
