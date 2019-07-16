package swo.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import swo.model.entities.SwoRole;
import swo.model.manager.ManagerSwoRole;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanSwoRole implements Serializable{
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerSwoRole managerSwoRole;
	private List<SwoRole> listaSwoRoles;
	private SwoRole swoRole;
	private boolean panelColapsado;
	private SwoRole swoRoleSeleccionado;
	
	@PostConstruct
	public void inicializar() {
		listaSwoRoles=managerSwoRole.findAllSwoRoles();
		swoRole= new SwoRole();
		panelColapsado=true;
	}
	
	public void actionListenerColapsarPanel() {
		panelColapsado=!panelColapsado;
	}
	
	public void actionListenerInsertarSwoRole() {
		try {
			managerSwoRole.insertarSwoRole(swoRole);
			listaSwoRoles = managerSwoRole.findAllSwoRoles(); 
			swoRole = new SwoRole();
			JSFUtil.crearMensajeInfo("Rol insertado");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public void actionListenerEliminarSwoRole(Integer codigoRol) {
		managerSwoRole.eliminarSwoRole(codigoRol);
		listaSwoRoles=managerSwoRole.findAllSwoRoles();
		JSFUtil.crearMensajeInfo("Rol eliminado");
	}
	
	public void actionListenerSeleccionarSwoRole(SwoRole swoRole) {
		swoRoleSeleccionado=swoRole;
	}
	
	public void actionListenerActualizarSwoRole() {
		try {
			managerSwoRole.actualizarSwoRole(swoRoleSeleccionado);
			listaSwoRoles=managerSwoRole.findAllSwoRoles();
			JSFUtil.crearMensajeInfo("Datos actulizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<SwoRole> getListaSwoRoles() {
		return listaSwoRoles;
	}

	public void setListaSwoRoles(List<SwoRole> listaSwoRoles) {
		this.listaSwoRoles = listaSwoRoles;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}

	public SwoRole getSwoRoleSeleccionado() {
		return swoRoleSeleccionado;
	}

	public void setSwoRoleSeleccionado(SwoRole swoRoleSeleccionado) {
		this.swoRoleSeleccionado = swoRoleSeleccionado;
	}

	public SwoRole getSwoRole() {
		return swoRole;
	}

	public void setSwoRole(SwoRole swoRole) {
		this.swoRole = swoRole;
	}
	
}
