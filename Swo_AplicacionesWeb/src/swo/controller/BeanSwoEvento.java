package swo.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import swo.model.entities.SwoEvento;
import swo.model.manager.ManagerSwoEvento;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanSwoEvento implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerSwoEvento managerSwoEvento;
	private List<SwoEvento> listaSwoEventos;
	private SwoEvento swoEvento;
	
	@PostConstruct
	public void inicializar() {
		listaSwoEventos=managerSwoEvento.findAllSwoEventos();
		swoEvento = new SwoEvento();
	}
	
	public void actionListenerInsertarSwoEvento() {
		try {
			managerSwoEvento.insertarSwoEvento(swoEvento);
			listaSwoEventos = managerSwoEvento.findAllSwoEventos(); 
			swoEvento = new SwoEvento();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}	
	}

	public List<SwoEvento> getListaSwoEventos() {
		return listaSwoEventos;
	}

	public void setListaSwoEventos(List<SwoEvento> listaSwoEventos) {
		this.listaSwoEventos = listaSwoEventos;
	}

	public SwoEvento getSwoEvento() {
		return swoEvento;
	}

	public void setSwoEvento(SwoEvento swoEvento) {
		this.swoEvento = swoEvento;
	}
	
	
}
