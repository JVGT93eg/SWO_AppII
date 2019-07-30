package swo.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import swo.model.entities.SwoCategoria;
import swo.model.entities.SwoTratamiento;
import swo.model.manager.ManagerTratamiento;


@Named
@SessionScoped
public class BeanTratamiento implements Serializable{
	private static final  long serialVersionUID = 1L;
	
	@EJB
	private ManagerTratamiento managerTratamiento;
	private List<SwoTratamiento> lista;
	private List<SwoCategoria>listaC;
	private SwoTratamiento tratamiento;
    private boolean panelColapso;
    private SwoTratamiento tratamientoSeleccionado;
    
    private String descripcionTra;
    private String estadoTra;
    private double precioTra;
    private int cod_Categoria;
    private SwoCategoria categoria; 

    
    @PostConstruct
    public void inicio() {
    	lista = managerTratamiento.listarTratamiento();
    	listaC = managerTratamiento.listarCategorias();
    	tratamiento = new SwoTratamiento();
    	categoria = new SwoCategoria();
    	panelColapso = true;
    			
    }
    
    public void actionListenerColapsarPanel() {
		panelColapso=!panelColapso;
	}
    public void actionListenerInsertarTratamiento() {
    	managerTratamiento.insertarTratamiento(descripcionTra, estadoTra, cod_Categoria, precioTra);
    	lista = managerTratamiento.listarTratamiento();  	
    	
    }
     
	
	public void actionListenerEliminartratamiento(int codigoTra) {
		managerTratamiento.eliminarTratamiento(codigoTra);
		lista = managerTratamiento.listarTratamiento();
		JSFUtil.crearMensajeInfo("Tratamiento Eliminado");
	}
	
	public void actionListenerSeleccionarTratamiento(SwoTratamiento tratamiento) {
		tratamientoSeleccionado=tratamiento;
	}
	
	public void actionListenerActualizarTratamiento() {
		try {
			managerTratamiento.actualizarTratamiento(tratamientoSeleccionado, cod_Categoria);
			lista = managerTratamiento.listarTratamiento();
			JSFUtil.crearMensajeInfo("Datos Actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
/////////////////////////////////////////////////////////////	
	public List<SwoTratamiento> getLista() {
		return lista;
	}

	public void setLista(List<SwoTratamiento> lista) {
		this.lista = lista;
	}

	public SwoTratamiento getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(SwoTratamiento tratamiento) {
		this.tratamiento = tratamiento;
	}

	public SwoTratamiento getTratamientoSeleccionado() {
		return tratamientoSeleccionado;
	}

	public void setTratamientoSeleccionado(SwoTratamiento tratamientoSeleccionado) {
		this.tratamientoSeleccionado = tratamientoSeleccionado;
	}

	public String getDescripcionTra() {
		return descripcionTra;
	}

	public void setDescripcionTra(String descripcionTra) {
		this.descripcionTra = descripcionTra;
	}

	public String getEstadoTra() {
		return estadoTra;
	}

	public void setEstadoTra(String estadoTra) {
		this.estadoTra = estadoTra;
	}

	public double getPrecioTra() {
		return precioTra;
	}

	public void setPrecioTra(double precioTra) {
		this.precioTra = precioTra;
	}

	public SwoCategoria getCategoria() {
		return categoria;
	}

	public void setCategoria(SwoCategoria categoria) {
		this.categoria = categoria;
	}

	public boolean isPanelColapso() {
		return panelColapso;
	}

	public void setPanelColapso(boolean panelColapso) {
		this.panelColapso = panelColapso;
	}

	public List<SwoCategoria> getListaC() {
		return listaC;
	}

	public void setListaC(List<SwoCategoria> listaC) {
		this.listaC = listaC;
	}

	public int getCod_Categoria() {
		return cod_Categoria;
	}

	public void setCod_Categoria(int cod_Categoria) {
		this.cod_Categoria = cod_Categoria;
	}
	
	
//////////////////////////////////////////////////////////////////
	

}
