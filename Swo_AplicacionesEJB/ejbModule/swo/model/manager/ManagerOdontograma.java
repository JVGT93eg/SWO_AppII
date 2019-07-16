package swo.model.manager;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import swo.model.entities.SwoCara;
import swo.model.entities.SwoDiente;
import swo.model.entities.SwoOdontograma;
import swo.model.entities.SwoPaciente;
import swo.model.entities.SwoTratamiento;







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
    public void insertarOdontograma(int codpac, Date fecha_ate,String descripción_ate,int codtrata,int coddie, int codcar) throws Exception {
  	SwoOdontograma odontograma=new SwoOdontograma();
  	SwoPaciente paciente=buscarPorCodigoPac(codpac);
  	SwoDiente diente=buscarPorCodigoDie(coddie);
  	SwoCara cara=buscarPorCodigoCar(codcar);
  	SwoTratamiento trata=buscarPorCodigoTrata(codtrata);
  	
    odontograma.setDescripcionAte(descripción_ate);
  	odontograma.setFechaAte(fecha_ate);
  	odontograma.setSwoTratamiento(trata);
  	odontograma.setSwoPaciente(paciente);	
   odontograma.setSwoDiente(diente);
   odontograma.setSwoCara(cara);

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
	 //Busca por codigo al Paciente
	 public SwoPaciente buscarPorCodigoPac(int codPa) {
		   SwoPaciente genero = em.find(SwoPaciente.class, codPa);
		   return genero;
}
	 //Busca por codigo al diente
	 public SwoDiente buscarPorCodigoDie(int codDie) {
		   SwoDiente diente = em.find(SwoDiente.class, codDie);
		   return diente;
}
	 //Busca por codigo al cara
	 public SwoCara buscarPorCodigoCar(int codCar) {
		   SwoCara cara = em.find(SwoCara.class, codCar);
		   return cara;
}
	 //Busca por codigo al tratamiento
	 public SwoTratamiento buscarPorCodigoTrata(int codtrat) {
		   SwoTratamiento tratam = em.find(SwoTratamiento.class, codtrat);
		   return tratam;
}
	// método de busqueda para el Odontograma
		public SwoOdontograma findSwoOdontogramaBycodigoOdon(Integer codigo_Odonto) {
			return em.find(SwoOdontograma.class, codigo_Odonto);

		}
		
		// Método que retorna la lista de la tabla Pacientes
		public List<SwoPaciente> listarPacie() {
			String consulta = ("SELECT s FROM SwoPaciente s");
			Query q = em.createQuery(consulta, SwoPaciente.class);
			return q.getResultList();

		}
}
