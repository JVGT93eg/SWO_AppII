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

import org.wildfly.security.password.TwoWayPassword;

import swo.model.entities.SwoArticulo;
import swo.model.entities.SwoCara;
import swo.model.entities.SwoCategoria;
import swo.model.entities.SwoDiente;
import swo.model.entities.SwoOdontograma;
import swo.model.entities.SwoPaciente;
import swo.model.entities.SwoProceArticulo;
import swo.model.entities.SwoProcedimiento;
import swo.model.entities.SwoTratamiento;







/**
 * Session Bean implementation class ManagerOdontograma
 */
@Stateless
@LocalBean
public class ManagerOdontograma {
@PersistenceContext
private EntityManager em;
@EJB
private ManagerDAO managerDAO;
@EJB
private MangerPaciente managerPacientes;
@EJB
private ManagerTratamiento managertratamiento;
@EJB
private ManagerCategorias managerCategoria;
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
    public void insertarOdontograma(SwoOdontograma odontogramacabTemp, int codpac,String descripcion_ate,int codtrata,int coddie, int codcar) throws Exception {
  	

    	SwoOdontograma odontograma=new SwoOdontograma();

  	SwoPaciente paciente=buscarPorCodigoPac(codpac);
  	
  	SwoDiente diente=buscarPorCodigoDie(coddie);
  	SwoCara cara=buscarPorCodigoCar(codcar);
  	SwoTratamiento trata=buscarPorCodigoTrata(codtrata);
  	
    odontograma.setDescripcionAte(descripcion_ate);
  	odontograma.setFechaAte(odontogramacabTemp.getFechaAte());
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
		/**
		 * Crea una nueva cabecera de factura temporal, para que desde el programa
		 * cliente pueda manipularla y llenarle con la informacion respectiva.
		 * Esta informacion solo se mantiene en memoria.
		 * @return la nueva factura temporal.
		 */
		public SwoOdontograma crearOdontogramaTmp(){
			SwoOdontograma OdontogramaCabTmp=new SwoOdontograma();
			OdontogramaCabTmp.setFechaAte(new Date());
	//		OdontogramaCabTmp.setSwoTratamiento(new ArrayList<SwoTratamiento>());
			return OdontogramaCabTmp;
		}
		
		public void asignarPacienteOdontogramaTemp(SwoOdontograma odontogramacabTemp, int codigoPac)  throws Exception
		{
				SwoPaciente paciente=null;
			if(codigoPac==0 ) 
			      throw new Exception("Error debe especificar el codigo del Paciente");
			try   {
			paciente = managerPacientes.finPacientes_ById(codigoPac);
			if(paciente==null)
				throw new Exception(codigoPac+ "Error al asignar paciente."+paciente);
			odontogramacabTemp.setSwoPaciente(paciente);
			}catch (Exception e) {
				e.printStackTrace();
				throw new Exception("Error al asignar paciente:"+e.getMessage());
			}
			
		}
		/**
		 * Adiciona un item detalle a una factura temporal. Estos valores permanecen
		 * en memoria. 
		 * @param codigoProducto codigo del producto.
		 * @param cantidad cantidad del producto.
		 * @throws Exception problemas ocurridos al momento de insertar el item detalle.
		 */
//		public void agregarDetalleOdontogramaTmp(SwoOdontograma OdontogramaCabTmp,Integer codigocatego,Integer codArticulo, Integer cantidad, Integer precio) throws Exception{
//			SwoCategoria ca;
//			SwoOdontograma od;	
//			SwoTratamiento tr;
//			SwoProcedimiento pr;
//			SwoProceArticulo prar;
//			SwoArticulo ar;
//			
//			
//			if(OdontogramaCabTmp==null)
//				throw new Exception("Error primero debe crear una nueva Odontograma.");
//			if(codigocatego==null||codigocatego.intValue()<0)
//				throw new Exception("Error debe especificar el codigo del tratamiento.");
//			if(codArticulo==null||codArticulo.intValue()<0)
//				throw new Exception("Error debe especificar el codigo del Diente.");
//			if(cantidad==null||cantidad.intValue()<0)
//				throw new Exception("Error debe especificar el codigo de la cara.");
//			if(precio==null||precio.intValue()<=0)
//				throw new Exception("Error debe especificar la cantidad del precio.");
//			
//			//buscamos la categoria:
//			ca=managerCategoria.findCategoriaById(codigocatego);
//			//creamos un nuevo tratamiento y llenamos sus propiedades:
//			tr=new SwoTratamiento();
//			tr.setPrecioTra(precio);
//			
//			//creamos un nuevo procedimiento
//			pr=new SwoProcedimiento();
//			pr.setCantidadProc(cantidad);
//			
//			//Creamos un nuevo Articulos
//			ar=new SwoArticulo();
//			ar.setCodigoArt(codArticulo);
//			
//			//verificamos los campos calculados:
//	//		calcularFacturaTmp(facturaCabTmp);
//		}
		
		public List<SwoArticulo> listarArticulo(){
			String sentencia = "SELECT s FROM SwoArticulo s";
			Query q = em.createQuery(sentencia,SwoArticulo.class);
			return q.getResultList();
		}
		 // Método que retorna la lista de  la tabla Dientes
	    public List<SwoDiente> findAllDientes(){
	    	String consulta=("SELECT s FROM SwoDiente s");
	    	Query q=em.createQuery(consulta,SwoDiente.class);
	    	return q.getResultList();
	    }
	    // Método que retorna la lista de  la tabla Cara
	    public List<SwoCara> findAllCaras(){
	    	String consulta=("SELECT s FROM SwoCara s");
	    	Query q=em.createQuery(consulta,SwoCara.class);
	    	return q.getResultList();
	    }
		
	    public List<SwoCategoria> listarCategorias(){
			Query q;
			String sentencia = "SELECT s FROM SwoCategoria s";
			q = em.createQuery(sentencia,SwoCategoria.class);
			return q.getResultList();
		}
	    public List<SwoTratamiento> listarTratamiento(){
			String sentencia = "SELECT s FROM SwoTratamiento s";
			Query q = em.createQuery(sentencia,SwoTratamiento.class);
			return q.getResultList();
		}
}
