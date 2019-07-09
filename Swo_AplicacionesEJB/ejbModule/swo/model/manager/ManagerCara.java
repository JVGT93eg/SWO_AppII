package swo.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import swo.model.entities.SwoCara;
import swo.model.entities.SwoDiente;



/**
 * Session Bean implementation class ManagerCara
 */
@Stateless
@LocalBean
public class ManagerCara {
	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public ManagerCara() {
        // TODO Auto-generated constructor stub
    }
    // Método que retorna la lista de  la tabla Cara
    public List<SwoCara> findAllCaras(){
    	String consulta=("SELECT s FROM SwoCara s");
    	Query q=em.createQuery(consulta,SwoCara.class);
    	return q.getResultList();
    }
    // método de busqueda de cara
    public SwoCara findSwoCaraBycodigocara(Integer codigo_cara)
    {
  	  return em.find(SwoCara.class, codigo_cara);
  	  
    }
    
  //para actualizar
    public void actualizarSwoCara(SwoCara cara) throws Exception {
  	  SwoCara e=findSwoCaraBycodigocara(cara.getCodigoCar());
  	  if(e==null)
  		  throw new Exception("No existe la cara del  Diente con la código especificado");
  	  e.setNombreCar(cara.getNombreCar());
  	  e.setDescripcionCar(cara.getDescripcionCar());
  	  em.merge(e);
    }
    
    //Insertar Cara
    public void insertarCara(SwoCara cara) {
    	em.persist(cara);   			
    }
    //Eliminar Cara
    public void eliminarCara(Integer codigo_cara) {
    	SwoCara cara=findSwoCaraBycodigocara(codigo_cara);
    	if(cara!=null)
    		em.remove(cara);
    }
    
}
