package swo.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.sun.nio.zipfs.JarFileSystemProvider;

import swo.model.entities.SwoTipoTransaccion;
import swo.model.manager.ManagerSwoTipoTransaccion;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanSwoTipoTratamiento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ManagerSwoTipoTransaccion managerTipoTransaccion;
	private List<SwoTipoTransaccion> listaTipoTransaccion;
	private SwoTipoTransaccion swoTipoTransaccion;
	private boolean panelColapsado;
	private SwoTipoTransaccion swoTipoTransSeleccionado;
	
	@PostConstruct
	public void inicializar() {
		listaTipoTransaccion=managerTipoTransaccion.findAllTipoTransaccion();
		swoTipoTransaccion=new SwoTipoTransaccion();
		panelColapsado = true;
	}
	public void actionListenerColapsarPanel() {
		panelColapsado=!panelColapsado;
	}
	public void actionListenerInsertarTipoTransaccion() {
		try {
			managerTipoTransaccion.insertTipoTransaccion(swoTipoTransaccion);
			listaTipoTransaccion=managerTipoTransaccion.findAllTipoTransaccion();
			swoTipoTransaccion=new SwoTipoTransaccion();
			JSFUtil.crearMensajeInfo("Tipo de Transaccion Registrado");
		}catch (Exception ex) {
			JSFUtil.crearMensajeError("Error: "+ex.getMessage());
		}
	}
	public void actionListenerEliminarTipoTransaccion(int codigoTipoTrans) {
		try {
			managerTipoTransaccion.deleteTipoTransaccion(codigoTipoTrans);
			listaTipoTransaccion=managerTipoTransaccion.findAllTipoTransaccion();
			JSFUtil.crearMensajeWarning("Tipo de Transaccion Eliminado");
		}catch(Exception ex) {
			JSFUtil.crearMensajeError("Error: "+ex.getMessage());
		}
	}
	public void actionListenerSeleccionarTipoTransaccion(SwoTipoTransaccion swoTipoTrans) {
		swoTipoTransSeleccionado=swoTipoTrans;
	}
	public void actionListenerActualizarSwoTipoTransaccion() {
		try {
			managerTipoTransaccion.updateTipoTransaccion(swoTipoTransSeleccionado);
			listaTipoTransaccion=managerTipoTransaccion.findAllTipoTransaccion();
			JSFUtil.crearMensajeInfo("Tipo de transaccion Actualizado");
		}catch(Exception ex) {
			JSFUtil.crearMensajeError("Error: "+ex.getMessage());
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
	public List<SwoTipoTransaccion> getListaTipoTransaccion() {
		return listaTipoTransaccion;
	}
	public void setListaTipoTransaccion(List<SwoTipoTransaccion> listaTipoTransaccion) {
		this.listaTipoTransaccion = listaTipoTransaccion;
	}
	public SwoTipoTransaccion getSwoTipoTransaccion() {
		return swoTipoTransaccion;
	}
	public void setSwoTipoTransaccion(SwoTipoTransaccion swoTipoTransaccion) {
		this.swoTipoTransaccion = swoTipoTransaccion;
	}
	public boolean isPanelColapsado() {
		return panelColapsado;
	}
	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}
	public SwoTipoTransaccion getSwoTipoTransSeleccionado() {
		return swoTipoTransSeleccionado;
	}
	public void setSwoTipoTransSeleccionado(SwoTipoTransaccion swoTipoTransSeleccionado) {
		this.swoTipoTransSeleccionado = swoTipoTransSeleccionado;
	}
	
}
