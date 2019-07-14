package swo.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import swo.model.entities.SwoGenero;
import swo.model.entities.SwoPaciente;
import swo.model.manager.MangerPaciente;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Named
@SessionScoped
public class BeanSwoPaciente implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private MangerPaciente managerPaciente;
	private List<SwoPaciente> listaPacientes;
	private List<SwoGenero> ListaG;
	private SwoGenero genero;
	
	
	private String email;
	private String telefono;
	private String direccion;
	private Date fecha_naci;
	private String apellido;
	private String nombre;
	private String cedula;
	private int codgenero;
	
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
    	genero=new SwoGenero();
	}
	public void actionListenerInsertarPaciente() {
		try {
			managerPaciente.insertarPaciente(cedula,nombre,apellido,fecha_naci,codgenero,direccion,telefono,email);
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
	public SwoGenero getGenero() {
		return genero;
	}
	public void setGenero(SwoGenero genero) {
		this.genero = genero;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Date getFecha_naci() {
		return fecha_naci;
	}
	public void setFecha_naci(Date fecha_naci) {
		this.fecha_naci = fecha_naci;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public int getCodgenero() {
		return codgenero;
	}
	public void setCodgenero(int codgenero) {
		this.codgenero = codgenero;
	}
	
	
}
