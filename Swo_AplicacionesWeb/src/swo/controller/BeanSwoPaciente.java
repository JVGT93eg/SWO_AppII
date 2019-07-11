package swo.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import swo.model.entities.SwoGenero;
import swo.model.entities.SwoPaciente;
import swo.model.manager.MangerPaciente;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanSwoPaciente implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private MangerPaciente managerPaciente;
	private List<SwoPaciente> listaPacientes;
	private List<SwoGenero> ListaG;
	
	
	public boolean isPanelColapsado() {
		return panelColapsado;
	}
	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}
	private SwoPaciente paciente;
	private boolean panelColapsado;
	private SwoPaciente pacienteSelecionado;
	
	@PostConstruct
    public void inicializar() {
    	listaPacientes=managerPaciente.findAllPacientes();
    	ListaG= managerPaciente.listarGenero();
    	paciente=new SwoPaciente();
    	panelColapsado=true;
    	
	}
	public void actionListenerInsertarPaciente() {
		try {
			managerPaciente.insertarPaciente(paciente);
			listaPacientes=managerPaciente.findAllPacientes();
			paciente=new SwoPaciente();
			JSFUtil.crearMensajeInfo("Datos de paciente Insertados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	public void actionListenerSeleccinarPaciente(SwoPaciente paciente) {
		pacienteSelecionado=paciente;
	
	}
	public void actionListenerColapsarPanel() {
		panelColapsado=!panelColapsado;
	}

	public void actionListenerActualizarPaciente() {
		try {
			managerPaciente.actualizarSwoPaciente(pacienteSelecionado);
			listaPacientes=managerPaciente.findAllPacientes();
			JSFUtil.crearMensajeInfo("Actualizado Correctamente");
		} catch (Exception e) {
		JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void actionListenerEliminarPaciente(Integer codigo_paci) {
		managerPaciente.eliminarPaciente(codigo_paci);
		listaPacientes=managerPaciente.findAllPacientes();
		JSFUtil.crearMensajeInfo("Paciente eliminado");

	}

	public List<SwoPaciente> getListaPacientes() {
		return listaPacientes;
	}
	public void setListaPacientes(List<SwoPaciente> listaPacientes) {
		this.listaPacientes = listaPacientes;
	}
	public SwoPaciente getPaciente() {
		return paciente;
	}
	public void setPaciente(SwoPaciente paciente) {
		this.paciente = paciente;
	}
	public SwoPaciente getPacienteSelecionado() {
		return pacienteSelecionado;
	}
	public void setPacienteSelecionado(SwoPaciente pacienteSelecionado) {
		this.pacienteSelecionado = pacienteSelecionado;
	}
	public List<SwoGenero> getListaG() {
		return ListaG;
	}
	public void setListaG(List<SwoGenero> listaG) {
		ListaG = listaG;
	}
	
	
}
