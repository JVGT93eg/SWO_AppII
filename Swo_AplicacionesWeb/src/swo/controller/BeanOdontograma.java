package swo.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import swo.model.entities.SwoOdontograma;
import swo.model.entities.SwoPaciente;
import swo.model.manager.ManagerOdontograma;
import swo.model.manager.MangerPaciente;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Named
@SessionScoped
public class BeanOdontograma implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerOdontograma managerOdontograma;
	private List<SwoOdontograma> listaOdontograma;
	private List<SwoPaciente> listaPaciente;
	private SwoPaciente paciente;
	private SwoOdontograma odontograma;
	private boolean panelColapsado;
	private SwoPaciente pacienteSelecionado;
	
	//datos para insertar las claves foraneás
	private String descripcion_ate;
	private Date fecha_ate;
	private int codtrata;
	private int coddie;
	private int codcar;
	private int codpac;
	

	@PostConstruct
    public void inicializar() {
	listaOdontograma =managerOdontograma.findAllOdontograma();
	listaPaciente = managerOdontograma.listarPacie();
	odontograma=new SwoOdontograma();
	paciente=new SwoPaciente();
	panelColapsado=true;
	}
	
	public void actionListenerInsertarOdontograma() {
		try {
			managerOdontograma.insertarOdontograma(codpac, fecha_ate, descripcion_ate, codtrata, coddie, codcar);
			listaOdontograma=managerOdontograma.findAllOdontograma();
			odontograma=new SwoOdontograma();
			JSFUtil.crearMensajeInfo("Datos de Odontograma Insertados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	public void actionListenerColapsarPanel() {
		panelColapsado=!panelColapsado;
	}
	public void actionListenerSeleccinarPaciente(SwoPaciente paciente) {
		pacienteSelecionado=paciente;
	
	}
	public List<SwoOdontograma> getListaOdontograma() {
		return listaOdontograma;
	}
	public void setListaOdontograma(List<SwoOdontograma> listaOdontograma) {
		this.listaOdontograma = listaOdontograma;
	}
	public List<SwoPaciente> getListaPaciente() {
		return listaPaciente;
	}
	public void setListaPaciente(List<SwoPaciente> listaPaciente) {
		this.listaPaciente = listaPaciente;
	}
	public SwoPaciente getPaciente() {
		return paciente;
	}
	public void setPaciente(SwoPaciente paciente) {
		this.paciente = paciente;
	}
	public SwoOdontograma getOdontograma() {
		return odontograma;
	}
	public void setOdontograma(SwoOdontograma odontograma) {
		this.odontograma = odontograma;
	}
	public boolean isPanelColapsado() {
		return panelColapsado;
	}
	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}

	public String getDescripcion_ate() {
		return descripcion_ate;
	}

	public void setDescripcion_ate(String descripcion_ate) {
		this.descripcion_ate = descripcion_ate;
	}

	public Date getFecha_ate() {
		return fecha_ate;
	}

	public void setFecha_ate(Date fecha_ate) {
		this.fecha_ate = fecha_ate;
	}

	public int getCodtrata() {
		return codtrata;
	}

	public void setCodtrata(int codtrata) {
		this.codtrata = codtrata;
	}

	public int getCoddie() {
		return coddie;
	}

	public void setCoddie(int coddie) {
		this.coddie = coddie;
	}

	public int getCodcar() {
		return codcar;
	}

	public void setCodcar(int codcar) {
		this.codcar = codcar;
	}

	public int getCodpac() {
		return codpac;
	}

	public void setCodpac(int codpac) {
		this.codpac = codpac;
	}

	public SwoPaciente getPacienteSelecionado() {
		return pacienteSelecionado;
	}

	public void setPacienteSelecionado(SwoPaciente pacienteSelecionado) {
		this.pacienteSelecionado = pacienteSelecionado;
	}
	
	
}
