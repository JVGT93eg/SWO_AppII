package swo.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import swo.model.entities.SwoEvento;
import swo.model.manager.ManagerSwoAuditoria;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanSwoAuditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerSwoAuditoria managerSwoAuditoria;
	private List<SwoEvento> listaSwoEventos;
	private SwoEvento swoEvento;
	
	@PostConstruct
	private void inicializar() {
		listaSwoEventos=managerSwoAuditoria.findAllSwoEventos();
		swoEvento = new SwoEvento();
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
