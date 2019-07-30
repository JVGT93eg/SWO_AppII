package swo.model.manager;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import swo.model.entities.SwoEvento;
import swo.model.entities.SwoUsuario;

/**
 * Session Bean implementation class ManagerSwoAuditoria
 */
@Stateless
@LocalBean
public class ManagerSwoAuditoria {
	@EJB
	private ManagerDAO managerDAO;
	@EJB
	private ManagerSeguridad managerSeguridad;
	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public ManagerSwoAuditoria() {
        // TODO Auto-generated constructor stub
    }
    
    public void crearEvento(String cedulaUsu,Class clase,String nombreEve,String descripcionEve) throws Exception{
    	SwoEvento swoEvento=new SwoEvento();
    	
    	if(cedulaUsu==null||cedulaUsu.length()==0)
			throw new Exception("Error auditoria: debe indicar el codigo del usuario.");
		if(nombreEve==null||nombreEve.length()==0)
			throw new Exception("Error auditoria: debe indicar el metodo que genera el evento.");
		
		SwoUsuario swoUsuario=(SwoUsuario)managerDAO.findById(SwoUsuario.class, cedulaUsu);
		if(swoUsuario==null)
			throw new Exception("Error auditoria: no existe el usuario indicado.");
		
		swoEvento.setSwoUsuario(swoUsuario);
		swoEvento.setNombreEve(clase.getSimpleName()+"/"+nombreEve);
		swoEvento.setDescripcionEve(descripcionEve);
		swoEvento.setFechaEve(new Date());
		swoEvento.setIpEve("localhost");
		
		managerDAO.insertar(swoEvento);
    }
    
    public List<SwoEvento> findAllSwoEventos(){
    	String consulta="SELECT s FROM SwoEvento s";
    	Query q=em.createQuery(consulta, SwoEvento.class);
    	return q. getResultList();
    }

}
