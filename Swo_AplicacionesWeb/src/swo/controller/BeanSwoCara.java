package swo.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import swo.model.entities.SwoCara;
import swo.model.entities.SwoDiente;
import swo.model.manager.ManagerCara;

@Named
@SessionScoped
public class BeanSwoCara implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerCara managerCara;
	private List<SwoCara> listaCaras;
	private SwoCara cara;
	private SwoCara caraSeleccionada;
	private boolean panelColapsado;

	@PostConstruct
	public void inicializar() {
		listaCaras = managerCara.findAllCaras();
		panelColapsado=true;
		cara =new SwoCara(); 
		
	}
	public void actionListenerColapsarPanel() {
		panelColapsado=!panelColapsado;
	}

	public void actionListenerSeleccinarCara(SwoCara cara) {
		caraSeleccionada = cara;

	}

	public void actionListenerActualizarCara() {
		try {
			managerCara.actualizarSwoCara(caraSeleccionada);
			listaCaras = managerCara.findAllCaras();
			JSFUtil.crearMensajeInfo("Actualizado Correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerInsertarCara() {
		try {
			managerCara.insertarCara(cara);
			listaCaras = managerCara.findAllCaras();
			cara = new SwoCara();
			JSFUtil.crearMensajeInfo("Cara insertada");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void actionListenerEliminarCara(Integer codigo_car) {
		managerCara.eliminarCara(codigo_car);
		listaCaras = managerCara.findAllCaras();
		JSFUtil.crearMensajeInfo("Cara eliminada");
	}

	public List<SwoCara> getListaCaras() {
		return listaCaras;
	}

	public void setListaCaras(List<SwoCara> listaCaras) {
		this.listaCaras = listaCaras;
	}

	public SwoCara getCara() {
		return cara;
	}

	public void setCara(SwoCara cara) {
		this.cara = cara;
	}

	public SwoCara getCaraSeleccionada() {
		return caraSeleccionada;
	}

	public void setCaraSeleccionada(SwoCara caraSeleccionada) {
		this.caraSeleccionada = caraSeleccionada;
	}
	public boolean isPanelColapsado() {
		return panelColapsado;
	}
	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}

}
