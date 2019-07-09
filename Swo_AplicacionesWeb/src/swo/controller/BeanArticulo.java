package swo.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import swo.model.entities.SwoArticulo;
import swo.model.manager.ManagerArticulo;





@Named
@SessionScoped
public class BeanArticulo implements Serializable{
	private static final long serialVersionUID = 1L;

	
	@EJB
	private ManagerArticulo managerArticulo;
	private List<SwoArticulo> lista;
	private SwoArticulo articulo;
    private boolean panelColapso;
    private SwoArticulo articuloSeleccionado;
    
    
    private String nombreArt;
    private String descripcionArt;
    private Date fechaExpiArt;
    private Date  fechaFabrArt; 
    private String marcaArt;
    private String riesgoArt;
    private String loteArt;
    private int maxArt; 
    private int minArt; 
    private int stockArt;
    
    ////////////////////////////////////////////////////////////////
    private List<Date> multi;
    private List<Date> range;
    private List<Date> invalidDates;
    private List<Integer> invalidDays;
    private Date minDate;
    private Date maxDate;
    ///////////////////////////////////////////////////////////////
    
    
    
    @PostConstruct
    public void inicializar() {
    	lista = managerArticulo.listarArticulo();
    	articulo = new SwoArticulo();
    	panelColapso = true;
    	
    	
    	//////////////////////////////
    	invalidDates = new ArrayList<>();
        Date today = new Date();
        invalidDates.add(today);
        long oneDay = 24 * 60 * 60 * 1000;
        for (int i = 0; i < 5; i++) {
            invalidDates.add(new Date(invalidDates.get(i).getTime() + oneDay));
        }
 
        invalidDays = new ArrayList<>();
        invalidDays.add(0); /* the first day of week is disabled */
        invalidDays.add(3);
 
        minDate = new Date(today.getTime() - (365 * oneDay));
        maxDate = new Date(today.getTime() + (365 * oneDay));
    }
    
    public void actionListenerColapsarPanel() {
		panelColapso=!panelColapso;
	}
    public void actionListenerInsertarArticulo() {
    	managerArticulo.insertarArticulo(nombreArt, descripcionArt, fechaExpiArt, fechaFabrArt, marcaArt, riesgoArt, loteArt, maxArt, minArt, stockArt);
    	lista = managerArticulo.listarArticulo();
    	
    }
	
	public void actionListenerEliminarCategoria(int codigoArt) {
		managerArticulo.eliminarArticulo(codigoArt);
		lista = managerArticulo.listarArticulo();
		JSFUtil.crearMensajeInfo("Articulo Eliminado");
	}
	
	public void actionListenerSeleccionarArticulo(SwoArticulo articulo) {
		articuloSeleccionado=articulo;
	}
	
	public void actionListenerActualizarArticulo() {
		try {
			managerArticulo.actualizarArticulo(articuloSeleccionado);
			lista = managerArticulo.listarArticulo();
			JSFUtil.crearMensajeInfo("Datos Actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/////////////////////////////////////////////
	
	 public void onDateSelect(SelectEvent event) {
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
	    }
	 
	    public void click() {
	        PrimeFaces.current().ajax().update("form:display");
	        PrimeFaces.current().executeScript("PF('dlg').show()");
	    }
	
	
	/////////////////////////////////////////////

	public ManagerArticulo getManagerArticulo() {
		return managerArticulo;
	}

	public void setManagerArticulo(ManagerArticulo managerArticulo) {
		this.managerArticulo = managerArticulo;
	}

	public List<SwoArticulo> getLista() {
		return lista;
	}

	public void setLista(List<SwoArticulo> lista) {
		this.lista = lista;
	}

	public SwoArticulo getArticulo() {
		return articulo;
	}

	public void setArticulo(SwoArticulo articulo) {
		this.articulo = articulo;
	}

	public boolean isPanelColapso() {
		return panelColapso;
	}

	public void setPanelColapso(boolean panelColapso) {
		this.panelColapso = panelColapso;
	}

	public SwoArticulo getArticuloSeleccionado() {
		return articuloSeleccionado;
	}

	public void setArticuloSeleccionado(SwoArticulo articuloSeleccionado) {
		this.articuloSeleccionado = articuloSeleccionado;
	}

	public String getNombreArt() {
		return nombreArt;
	}

	public void setNombreArt(String nombreArt) {
		this.nombreArt = nombreArt;
	}

	public String getDescripcionArt() {
		return descripcionArt;
	}

	public void setDescripcionArt(String descripcionArt) {
		this.descripcionArt = descripcionArt;
	}

	public Date getFechaExpiArt() {
		return fechaExpiArt;
	}

	public void setFechaExpiArt(Date fechaExpiArt) {
		this.fechaExpiArt = fechaExpiArt;
	}

	public Date getFechaFabrArt() {
		return fechaFabrArt;
	}

	public void setFechaFabrArt(Date fechaFabrArt) {
		this.fechaFabrArt = fechaFabrArt;
	}

	public String getMarcaArt() {
		return marcaArt;
	}

	public void setMarcaArt(String marcaArt) {
		this.marcaArt = marcaArt;
	}

	public String getRiesgoArt() {
		return riesgoArt;
	}

	public void setRiesgoArt(String riesgoArt) {
		this.riesgoArt = riesgoArt;
	}


	public String getLoteArt() {
		return loteArt;
	}

	public void setLoteArt(String loteArt) {
		this.loteArt = loteArt;
	}

	public int getMaxArt() {
		return maxArt;
	}

	public void setMaxArt(int maxArt) {
		this.maxArt = maxArt;
	}

	public int getMinArt() {
		return minArt;
	}

	public void setMinArt(int minArt) {
		this.minArt = minArt;
	}

	public int getStockArt() {
		return stockArt;
	}

	public void setStockArt(int stockArt) {
		this.stockArt = stockArt;
	}
	
	
}
    
    