package swo.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import swo.model.entities.SwoGenero;


/**
 * Session Bean implementation class ManagerSwoGenero
 */
@Stateless
@LocalBean
public class ManagerSwoGenero {
	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public ManagerSwoGenero() {
        // TODO Auto-generated constructor stub
    }
    // Método que retorna la lista de  la tabla Genero
    public List<SwoGenero> findAllGenero(){
    	String consulta=("SELECT s FROM SwoGenero s");
    	Query q=em.createQuery(consulta,SwoGenero.class);
    	return q.getResultList();
    	
 
    }
    //metodo para insertar un genero
    public void insertarGenero(SwoGenero genero) throws Exception {  		
  	  em.persist(genero);
    }
    // método de busqueda para el genero
    public SwoGenero findSwoGeneroBycodigoge(Integer codigo_ge)
    {
  	  return em.find(SwoGenero.class, codigo_ge);
  	  
    }
    
    //método para eliminar
    public void eliminarGenero(Integer codigo_ge) {
  	 SwoGenero genero=findSwoGeneroBycodigoge(codigo_ge);
  	  if(genero!=null)
  		  em.remove(genero);
    }
    
  //para actualizar
    public void actualizarSwoGenero(SwoGenero genero) throws Exception {
  	  SwoGenero e=findSwoGeneroBycodigoge(genero.getCodigoGen());
  	  if(e==null)
  		  throw new Exception("No existe el Género con la código especificado");
  	  e.setDescripcionGen(genero.getDescripcionGen());
  	  em.merge(e);
    }
    //método que funciona para la lista de género
//    public List<SwoGenero> listarGenero(){
//		
//		String sentencia = "SELECT s FROM SwoGenero s";
//		Query q = em.createQuery(sentencia,SwoGenero.class);
//		return q.getResultList();
//	}
}
