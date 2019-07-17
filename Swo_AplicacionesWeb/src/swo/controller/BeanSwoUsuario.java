package swo.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import swo.model.entities.SwoLogin;
import swo.model.entities.SwoRole;
import swo.model.entities.SwoUsuario;
import swo.model.manager.ManagerSwoLogin;
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
	private List<SwoUsuario> listaSwoUsuarios;
	private SwoUsuario swoUsuario;
	private boolean panelColapsado;
	private SwoUsuario swoUsuarioSeleccionado;
	
	//ADD
	@EJB
	private ManagerSwoLogin managerSwoLogin;
	@EJB
	private ManagerSwoRole managerSwoRol;
	private List<SwoRole> listaRoles;
	private int codigoRol;
	
	
	@PostConstruct
	public void inicializar() {
		listaSwoUsuarios=managerSwoUsuario.findAllSwoUsuarios();
		listaRoles=managerSwoRol.findAllSwoRoles();
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
			int codigoUsuario=-1;
			for (SwoUsuario swoU : listaSwoUsuarios) {
				if(swoU.getCedulaUsu().equals(swoUsuario.getCedulaUsu())) {
					codigoUsuario=swoU.getCodigoUsu();
				}
			}
			managerSwoLogin.insertarSwoLogin(codigoRol, codigoUsuario);
			swoUsuario = new SwoUsuario();
			JSFUtil.crearMensajeInfo("Usuario insertado");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public void actionListenerEliminarSwoUsuario(Integer codigoUsu) {
		List<SwoLogin> listaLogin=managerSwoLogin.findAllSwoLogin();
		for(SwoLogin log: listaLogin) {
			SwoUsuario us=log.getSwoUsuario();
			if(us.getCodigoUsu().equals(codigoUsu)) {
				managerSwoLogin.eliminarSwoLogin(log.getCodigoLogin());
			}
		}
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

	public List<SwoRole> getListaRoles() {
		return listaRoles;
	}

	public void setListaRoles(List<SwoRole> listaRoles) {
		this.listaRoles = listaRoles;
	}

	public int getCodigoRol() {
		return codigoRol;
	}

	public void setCodigoRol(int codigoRol) {
		this.codigoRol = codigoRol;
	}
	
	
}
