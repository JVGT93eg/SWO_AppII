package swo.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import swo.model.entities.SwoDiente;
import swo.model.manager.ManagerDiente;











@Named
@SessionScoped
public class BeanSwoDiente  implements Serializable{
	private static final long serialVersionUID = 1L;
 @EJB
	private ManagerDiente managerDiente;
	private List<SwoDiente> listaDientes;
	private SwoDiente diente;
	private SwoDiente dienteSeleccionado;
	private boolean panelColapsado;
	@PostConstruct
    public void inicializar() {
    	listaDientes=managerDiente.findAllDientes();
    	panelColapsado=true;
    	diente=new SwoDiente();
	}
	public void actionListenerColapsarPanel() {
		panelColapsado=!panelColapsado;
	}

	public List<SwoDiente> getListaDientes() {
		return listaDientes;
	}


	public void setListaDientes(List<SwoDiente> listaDientes) {
		this.listaDientes = listaDientes;
	}


	public void actionListenerSeleccinarDiente(SwoDiente diente) {
		dienteSeleccionado=diente;
	
	}
	
	
	public SwoDiente getDiente() {
		return diente;
	}


	public void setDiente(SwoDiente diente) {
		this.diente = diente;
	}


	public SwoDiente getDienteSeleccionado() {
		return dienteSeleccionado;
	}


	public void setDienteSeleccionado(SwoDiente dienteSeleccionado) {
		this.dienteSeleccionado = dienteSeleccionado;
	}


	public boolean isPanelColapsado() {
		return panelColapsado;
	}
	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}
	public void actionListenerActualizarDiente() {
		try {
			managerDiente.actualizarSwoDiente(dienteSeleccionado);
			listaDientes=managerDiente.findAllDientes();
			JSFUtil.crearMensajeInfo("Actualizado Correctamente");
		} catch (Exception e) {
		JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	public void actionListenerInsertarDiente() {
		try {
			managerDiente.insertarDiente(diente);
			listaDientes = managerDiente.findAllDientes(); 
			diente = new SwoDiente();
			JSFUtil.crearMensajeInfo("Diente insertado");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public void actionListenerEliminarDiente(Integer codigo_di) {
		managerDiente.eliminarDiente(codigo_di);
		listaDientes=managerDiente.findAllDientes();
		JSFUtil.crearMensajeInfo("Diente eliminado eliminado");
	}
	
	
}
