package swo.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import swo.model.entities.SwoUsuario;
import swo.model.manager.ManagerSwoUsuario;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanSwoUsuario implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerSwoUsuario managerSwoUsuario;
	private List<SwoUsuario> listaSwoUsuarios;
	private SwoUsuario swoUsuario;
	private boolean panelColapsado;
	private SwoUsuario swoUsuarioSeleccionado;
	
	
	@PostConstruct
	public void inicializar() {
		listaSwoUsuarios=managerSwoUsuario.findAllSwoUsuarios();
		swoUsuario = new SwoUsuario();
		panelColapsado=true;
	}
	
	public void actionListenerColapsarPanel() {
		panelColapsado=!panelColapsado;
	}
	
	public void actionListenerInsertarSwoUsuario() {
		try {
			managerSwoUsuario.insertarSwoUsuario(swoUsuario);
			listaSwoUsuarios = managerSwoUsuario.findAllSwoUsuarios(); 
			swoUsuario = new SwoUsuario();
			JSFUtil.crearMensajeInfo("Usuario insertado");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public void actionListenerEliminarSwoUsuario(Integer codigoUsu) {
		managerSwoUsuario.eliminarSwoUsuario(codigoUsu);
		listaSwoUsuarios=managerSwoUsuario.findAllSwoUsuarios();
		JSFUtil.crearMensajeInfo("Usuario eliminado");
	}
	
	public void actionListenerSeleccionarSwoUsuario(SwoUsuario swoUsuario) {
		swoUsuarioSeleccionado=swoUsuario;
	}
	
	public void actionListenerActualizarSwoUsuario() {
		try {
			managerSwoUsuario.actualizarSwoUsuario(swoUsuarioSeleccionado);
			listaSwoUsuarios=managerSwoUsuario.findAllSwoUsuarios();
			JSFUtil.crearMensajeInfo("Datos Actulizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerCrearMsjInf() {
		JSFUtil.crearMensajeInfo("Mensaje informativo");
	}
	
	public void actionListenerCrearMsjWarn() {
		JSFUtil.crearMensajeWarning("Mensaje de advertencia");
	}
	
	public void actionListenerCrearMsjError() {
		JSFUtil.crearMensajeError("Mensaje de error");
	}

	public List<SwoUsuario> getListaSwoUsuarios() {
		return listaSwoUsuarios;
	}

	public void setListaSwoUsuarios(List<SwoUsuario> listaSwoUsuarios) {
		this.listaSwoUsuarios = listaSwoUsuarios;
	}

	public SwoUsuario getSwoUsuario() {
		return swoUsuario;
	}

	public void setSwoUsuario(SwoUsuario swoUsuario) {
		this.swoUsuario = swoUsuario;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}

	public SwoUsuario getSwoUsuarioSeleccionado() {
		return swoUsuarioSeleccionado;
	}

	public void setSwoUsuarioSeleccionado(SwoUsuario swoUsuarioSeleccionado) {
		this.swoUsuarioSeleccionado = swoUsuarioSeleccionado;
	}
	
}
