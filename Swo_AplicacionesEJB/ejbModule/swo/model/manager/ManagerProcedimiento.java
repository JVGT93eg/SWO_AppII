package swo.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import swo.model.entities.SwoProcedimiento;
import swo.model.entities.SwoTratamiento;

@Stateless
@LocalBean
public class ManagerProcedimiento {
	
	@PersistenceContext
	private EntityManager em;
	
	public ManagerProcedimiento() {
		
	}
	
	public List<SwoProcedimiento> listarProcedimiento(){
		String sentencia = "SELECT s FROM SwoProcedimiento s";
		Query q = em.createQuery(sentencia,SwoProcedimiento.class);
		return q.getResultList();
	}
	   public SwoProcedimiento buscarporCodigo(int codiPro){
	        return em.find(SwoProcedimiento.class, codiPro);
	    }
		public List<SwoTratamiento> listarTratamiento(){
			Query q;
			String sentencia = "SELECT s FROM SwoTratamiento s";
			q = em.createQuery(sentencia,SwoTratamiento.class);
			return q.getResultList();
		}
	
	  	   public SwoTratamiento buscarPorCodigoT(int codigoTra) {
	  		   SwoTratamiento tratamiento = em.find(SwoTratamiento.class, codigoTra);
	  		   return tratamiento;
	   }
	   
	   
	   public void insertarProcedimineto(String descripcionProc, int cantidadProc, int codigoTra) {
	    	SwoProcedimiento procedimineto = new SwoProcedimiento();
	    	SwoTratamiento tratamieto = buscarPorCodigoT(codigoTra);
	    	procedimineto.setDescripcionPro(descripcionProc);
	    	procedimineto.setCantidadProc(cantidadProc);
	    	procedimineto.setSwoTratamiento(tratamieto);
	    	em.persist(procedimineto);
	    }
	   
	  
	    
	    public void eliminarProcedimiento(int codigoPro) {
	    	SwoProcedimiento procedimiento = buscarporCodigo(codigoPro);
	    	if(procedimiento!=null)
	    		em.remove(procedimiento);
	    }
	    
	    public void actualizarProcedimiento(SwoProcedimiento procedimiento) throws Exception {
	    	SwoProcedimiento e = buscarporCodigo(procedimiento.getCodigoPro());
	    	if(e==null)
	    		throw new Exception("No existe el tratamiento especificada.");
	    	e.setDescripcionPro(procedimiento.getDescripcionPro());
	    	e.setCantidadProc(procedimiento.getCantidadProc());
	    	e.setSwoTratamiento(procedimiento.getSwoTratamiento());
	    	em.merge(e);
	    	
	    }

}