package swo.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import swo.model.entities.SwoLogin;
import swo.model.entities.SwoRole;
import swo.model.entities.SwoUsuario;
import swo.model.manager.ManagerSwoLogin;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanSwoLogin implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerSwoLogin managerSwoLogin;
	private List<SwoLogin> listaSwoLogin;
	private List<SwoRole> listaSwoRol;
	private List<SwoUsuario> listaSwoUsuario;
	private SwoLogin swoLogin;
	private SwoRole swoRol;
	private SwoUsuario swoUsuario;
	private boolean panelColapsado;
	private SwoLogin swoLoginSeleccionado;
	private SwoRole swoRoleSeleccionado;
	private SwoUsuario swoUsuarioSeleccionado;
	
	@PostConstruct
	public void inicializar() {
		listaSwoLogin=managerSwoLogin.findAllSwoLogin();
		listaSwoRol=managerSwoLogin.findAllSwoRoles();
		listaSwoUsuario=managerSwoLogin.findAllSwoUsuarios();
		swoLogin = new SwoLogin();
	    swoRol = new SwoRole();
		swoUsuario = new SwoUsuario();
		panelColapsado=true;
	}

	public void actionListenerColapsarPanel() {
		panelColapsado=!panelColapsado;
	}
	
	public void actionListenerInsertarSwoLogin() {
		try {
			managerSwoLogin.insertarSwoLogin(swoLogin);
			listaSwoLogin = managerSwoLogin.findAllSwoLogin(); 
			swoLogin = new SwoLogin();
			JSFUtil.crearMensajeInfo("Rol insertado");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public void actionlistenerEliminarSwoLogin(Integer codigoLogin) {
		managerSwoLogin.eliminarSwoLogin(codigoLogin);
		listaSwoLogin=managerSwoLogin.findAllSwoLogin();
		JSFUtil.crearMensajeInfo("Login eliminado");
	}
	
	public void actionListenerSeleccionarswoLogin(SwoLogin swoLogin) {
		swoLoginSeleccionado=swoLogin;
	}
	public void actionListenerSeleccionarSwoRole(Integer codigoRol) {
		swoRoleSeleccionado=managerSwoLogin.findSwoRoleByCodigoRol(codigoRol);
	}
	
	public void actionListenerSeleccionarSwoUsuario(Integer codigoUsuario) {
		swoUsuarioSeleccionado=managerSwoLogin.findSwoUsuarioByCodigoUsu(codigoUsuario);
	}
	
	public void actionListenerActualizarSwoLogin() {
		try {
			managerSwoLogin.actualizarSwoLogin(swoLoginSeleccionado);
			listaSwoLogin=managerSwoLogin.findAllSwoLogin();
			JSFUtil.crearMensajeInfo("Datos actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<SwoLogin> getListaSwoLogin() {
		return listaSwoLogin;
	}

	public void setListaSwoLogin(List<SwoLogin> listaSwoLogin) {
		this.listaSwoLogin = listaSwoLogin;
	}

	public SwoLogin getSwoLogin() {
		return swoLogin;
	}

	public void setSwoLogin(SwoLogin swoLogin) {
		this.swoLogin = swoLogin;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}

	public SwoLogin getSwoLoginSeleccionado() {
		return swoLoginSeleccionado;
	}

	public void setSwoLoginSeleccionado(SwoLogin swoLoginSeleccionado) {
		this.swoLoginSeleccionado = swoLoginSeleccionado;
	}

	public List<SwoRole> getListaSwoRol() {
		return listaSwoRol;
	}

	public void setListaSwoRol(List<SwoRole> listaSwoRol) {
		this.listaSwoRol = listaSwoRol;
	}

	public List<SwoUsuario> getListaSwoUsuario() {
		return listaSwoUsuario;
	}

	public void setListaSwoUsuario(List<SwoUsuario> listaSwoUsuario) {
		this.listaSwoUsuario = listaSwoUsuario;
	}

	public SwoRole getSwoRol() {
		return swoRol;
	}

	public void setSwoRol(SwoRole swoRol) {
		this.swoRol = swoRol;
	}

	public SwoUsuario getSwoUsuario() {
		return swoUsuario;
	}

	public void setSwoUsuario(SwoUsuario swoUsuario) {
		this.swoUsuario = swoUsuario;
	}

	public SwoRole getSwoRoleSeleccionado() {
		return swoRoleSeleccionado;
	}

	public void setSwoRoleSeleccionado(SwoRole swoRoleSeleccionado) {
		this.swoRoleSeleccionado = swoRoleSeleccionado;
	}

	public SwoUsuario getSwoUsuarioSeleccionado() {
		return swoUsuarioSeleccionado;
	}

	public void setSwoUsuarioSeleccionado(SwoUsuario swoUsuarioSeleccionado) {
		this.swoUsuarioSeleccionado = swoUsuarioSeleccionado;
	}
	
}
