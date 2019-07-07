package swo.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import swo.model.entities.SwoProcedimiento;
import swo.model.entities.SwoTratamiento;
import swo.model.manager.ManagerProcedimiento;





@Named
@SessionScoped
public class BeanProcedimiento implements Serializable{
	private static final  long serialVersionUID = 1L;
	
	@EJB
	private ManagerProcedimiento managerProcedimiento;
	private List<SwoProcedimiento> lista;
	private List<SwoTratamiento>listaT;
	private SwoProcedimiento procedimiento;
    private boolean panelColapso;
    private SwoProcedimiento procedimientoSeleccionado;
    private SwoTratamiento tratamiento; 
    
    private String descripcionProc;
    private int cantidadProc;
    private int codigoTra;
    
    @PostConstruct
    public void inicializar() {
    	lista = managerProcedimiento.listarProcedimiento();
    	listaT = managerProcedimiento.listarTratamiento();
    	procedimiento = new SwoProcedimiento();
    	tratamiento = new SwoTratamiento();
    	panelColapso = true;
    }
    
    public void actionListenerColapsarPanel() {
		panelColapso=!panelColapso;
	}
    public void actionListenerInsertarProcedimiento() {
    	managerProcedimiento.insertarProcedimineto(descripcionProc, cantidadProc, codigoTra);
    	lista = managerProcedimiento.listarProcedimiento();
    	
    }
     
	
	public void actionListenerEliminarProcedimineto(int codigoPro) {
		managerProcedimiento.eliminarProcedimiento(codigoPro);
		lista = managerProcedimiento.listarProcedimiento();
		JSFUtil.crearMensajeInfo("Procedimiento Eliminado");
	}
	
	public void actionListenerSeleccionarProcedimiento(SwoProcedimiento procedimiento) {
		procedimientoSeleccionado=procedimiento;
	}
	
	public void actionListenerActualizarProcedimiento() {
		try {
			managerProcedimiento.actualizarProcedimiento(procedimientoSeleccionado);
			lista = managerProcedimiento.listarProcedimiento();
			JSFUtil.crearMensajeInfo("Datos Actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
////////////////////////////////////////
	
	public List<SwoProcedimiento> getLista() {
		return lista;
	}

	public void setLista(List<SwoProcedimiento> lista) {
		this.lista = lista;
	}

	public List<SwoTratamiento> getListaT() {
		return listaT;
	}

	public void setListaT(List<SwoTratamiento> listaT) {
		this.listaT = listaT;
	}

	public SwoProcedimiento getProcedimiento() {
		return procedimiento;
	}

	public void setProcedimiento(SwoProcedimiento procedimiento) {
		this.procedimiento = procedimiento;
	}

	public boolean isPanelColapso() {
		return panelColapso;
	}

	public void setPanelColapso(boolean panelColapso) {
		this.panelColapso = panelColapso;
	}

	public SwoProcedimiento getProcedimientoSeleccionado() {
		return procedimientoSeleccionado;
	}

	public void setProcedimientoSeleccionado(SwoProcedimiento procedimientoSeleccionado) {
		this.procedimientoSeleccionado = procedimientoSeleccionado;
	}

	public SwoTratamiento getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(SwoTratamiento tratamiento) {
		this.tratamiento = tratamiento;
	}

	public String getDescripcionProc() {
		return descripcionProc;
	}

	public void setDescripcionProc(String descripcionProc) {
		this.descripcionProc = descripcionProc;
	}

	public int getCantidadProc() {
		return cantidadProc;
	}

	public void setCantidadProc(int cantidadProc) {
		this.cantidadProc = cantidadProc;
	}

	public int getCodigoTra() {
		return codigoTra;
	}

	public void setCodigoTra(int codigoTra) {
		this.codigoTra = codigoTra;
	}
	
	///////////////////////////////////////////////////
	
}