package swo.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import swo.model.entities.SwoOdontograma;







/**
 * Session Bean implementation class ManagerOdontograma
 */
@Stateless
@LocalBean
public class ManagerOdontograma {
@PersistenceContext
private EntityManager em;
    /**
     * Default constructor. 
     */
    public ManagerOdontograma() {
        // TODO Auto-generated constructor stub
    }
    
    // Método que retorna la lista de  la tabla Odontograma
    public List<SwoOdontograma> findAllOdontograma(){
    	String consulta=("SELECT s FROM SwoOdontograma s");
    	Query q=em.createQuery(consulta,SwoOdontograma.class);
    	return q.getResultList();
    }
    
    // método de busqueda para el Odontograma
    public SwoOdontograma findSwoOdontogramaBycodigoate(Integer codigo_ate)
    {
  	  return em.find(SwoOdontograma.class, codigo_ate);
  	  
    }
    
    //metodo para insertar un Odontograma
    public void insertarOdontograma(SwoOdontograma odontograma) throws Exception {
  	  if(findSwoOdontogramaBycodigoate(odontograma.getCodigoAte())!=null)
  		  throw new Exception("Ya existe el código indicado.");
  	  em.persist(odontograma);
    }
    
    //métod para eliminar
    public void eliminarOdontograma(Integer codigo_ate) {
  	 SwoOdontograma odontograma=findSwoOdontogramaBycodigoate(codigo_ate);
  	  if(odontograma!=null)
  		  em.remove(odontograma);
    }
    
  //para actualizar
    public void actualizarSwoOdontograma(SwoOdontograma odontograma) throws Exception {
  	  SwoOdontograma e=findSwoOdontogramaBycodigoate(odontograma.getCodigoAte());
  	  if(e==null)
  		  throw new Exception("No existe el Odontograma con la código especificado");
  	  e.setFechaAte(odontograma.getFechaAte());
	  e.setDescripcionAte(odontograma.getDescripcionAte());
 // 	e.setSwoGenero(paciente.getSwoGenero());
  	  em.merge(e);
    }

}
