package swo.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import swo.model.entities.SwoRole;
import swo.model.entities.SwoUsuario;
import swo.model.manager.ManagerSwoRole;
import swo.model.manager.ManagerSwoUsuario;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanSwoUsuario implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerSwoUsuario managerSwoUsuario;
	@EJB
	private ManagerSwoRole managerSwoRole;
	private List<SwoUsuario> listaSwoUsuarios;
	private List<SwoRole> listaSwoRoles;
	private SwoUsuario swoUsuario;
	private boolean panelColapsado;
	private SwoUsuario swoUsuarioSeleccionado;
	private int codigoRol;
	
	@PostConstruct
	public void inicializar() {
		listaSwoUsuarios=managerSwoUsuario.findAllSwoUsuarios();
		listaSwoRoles=managerSwoRole.findAllSwoRoles();
		swoUsuario = new SwoUsuario();
		panelColapsado=true;
	}
	
	public void actionListenerColapsarPanel() {
		panelColapsado=!panelColapsado;
	}
	
	public void actionListenerInsertarSwoUsuario() {
		try {
			managerSwoUsuario.insertarSwoUsuario(swoUsuario, codigoRol);
			listaSwoUsuarios = managerSwoUsuario.findAllSwoUsuarios(); 
			swoUsuario = new SwoUsuario();
			JSFUtil.crearMensajeInfo("Usuario insertado");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public void actionListenerEliminarSwoUsuario(String cedulaUsu) {
		managerSwoUsuario.eliminarSwoUsuario(cedulaUsu);
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

	public int getCodigoRol() {
		return codigoRol;
	}

	public void setCodigoRol(int codigoRol) {
		this.codigoRol = codigoRol;
	}

	public List<SwoRole> getListaSwoRoles() {
		return listaSwoRoles;
	}

	public void setListaSwoRoles(List<SwoRole> listaSwoRoles) {
		this.listaSwoRoles = listaSwoRoles;
	}
	
}
