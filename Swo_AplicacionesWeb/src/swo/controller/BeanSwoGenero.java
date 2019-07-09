package swo.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import swo.model.entities.SwoGenero;
import swo.model.manager.ManagerSwoGenero;


@Named
@SessionScoped
public class BeanSwoGenero implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerSwoGenero managerSwoGenero;
	private List<SwoGenero> listaGenero;
	private SwoGenero genero;
	private boolean panelColapsado;
	private SwoGenero generoSeleccionado;
	
	@PostConstruct
    public void inicializar() {
    	listaGenero=managerSwoGenero.findAllGenero();
    	genero=new SwoGenero();
    	panelColapsado=true;
    	
	}
	
	public void actionListenerInsertarGenero() {
		try {
			managerSwoGenero.insertarGenero(genero);
			listaGenero=managerSwoGenero.findAllGenero();
			genero=new SwoGenero();
			JSFUtil.crearMensajeInfo("Datos de genero Insertados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerSeleccionarGenero(SwoGenero genero) {
		generoSeleccionado=genero;
	
	}
	public void actionListenerColapsarPanel() {
		panelColapsado=!panelColapsado;
	}

	public void actionListenerActualizarGenero() {
		try {
			managerSwoGenero.actualizarSwoGenero(generoSeleccionado);
			listaGenero=managerSwoGenero.findAllGenero();
			JSFUtil.crearMensajeInfo("Actualizado Correctamente");
		} catch (Exception e) {
		JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void actionListenerEliminarGenero(Integer codigo_ge) {
		managerSwoGenero.eliminarGenero(codigo_ge);
		listaGenero=managerSwoGenero.findAllGenero();
		JSFUtil.crearMensajeInfo("Genero eliminado");

	}

	public List<SwoGenero> getListaGenero() {
		return listaGenero;
	}

	public void setListaGenero(List<SwoGenero> listaGenero) {
		this.listaGenero = listaGenero;
	}

	public SwoGenero getGenero() {
		return genero;
	}

	public void setGenero(SwoGenero genero) {
		this.genero = genero;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}

	public SwoGenero getGeneroSeleccionado() {
		return generoSeleccionado;
	}

	public void setGeneroSeleccionado(SwoGenero generoSeleccionado) {
		this.generoSeleccionado = generoSeleccionado;
	}
	
}
