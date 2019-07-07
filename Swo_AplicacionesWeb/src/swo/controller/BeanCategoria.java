package swo.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import swo.model.entities.SwoCategoria;
import swo.model.manager.ManagerCategorias;


@Named
@SessionScoped
public class BeanCategoria implements Serializable{
	private static final long serialVersionUID = 1L;

	
	@EJB
	private ManagerCategorias managerCategoria;
	private List<SwoCategoria> lista;
	private SwoCategoria categorias;
    private boolean panelColapso;
    private SwoCategoria categoriaSeleccionada;
    private String nombreCat;
    private String descripcionCat;
    
    
    @PostConstruct
    public void inicializar() {
    	lista = managerCategoria.listarCategorias();
    	categorias = new SwoCategoria();
    	panelColapso = true;
    }
    
    public void actionListenerColapsarPanel() {
		panelColapso=!panelColapso;
	}
    public void actionListenerInsertarCategoria() {
    	managerCategoria.insertarCategoria(nombreCat, descripcionCat);
    	lista = managerCategoria.listarCategorias();  	
    	
    }
	
	public void actionListenerEliminarCategoria(int codCategoria) {
		managerCategoria.eliminarCategoria(codCategoria);
		lista = managerCategoria.listarCategorias();
		JSFUtil.crearMensajeInfo("Categoria Eliminado");
	}
	
	public void actionListenerSeleccionarCategoria(SwoCategoria categoria) {
		categoriaSeleccionada=categoria;
	}
	
	public void actionListenerActualizarCategoria() {
		try {
			managerCategoria.actualizarCategoria(categoriaSeleccionada);
			lista = managerCategoria.listarCategorias();
			JSFUtil.crearMensajeInfo("Datos Actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
    
    
    
    
    
    
    /////////////////////////////////////////////

	public List<SwoCategoria> getLista() {
		return lista;
	}
	public void setLista(List<SwoCategoria> lista) {
		this.lista = lista;
	}

	public SwoCategoria getCategorias() {
		return categorias;
	}


	public void setCategorias(SwoCategoria categorias) {
		this.categorias = categorias;
	}


	public boolean isPanelColapso() {
		return panelColapso;
	}


	public void setPanelColapso(boolean panelColapso) {
		this.panelColapso = panelColapso;
	}
	public SwoCategoria getCategoriaSeleccionada() {
		return categoriaSeleccionada;
	}
	public void setCategoriaSeleccionada(SwoCategoria categoriaSeleccionada) {
		this.categoriaSeleccionada = categoriaSeleccionada;
	}

	public String getNombreCat() {
		return nombreCat;
	}

	public void setNombreCat(String nombreCat) {
		this.nombreCat = nombreCat;
	}

	public String getDescripcionCat() {
		return descripcionCat;
	}

	public void setDescripcionCat(String descripcionCat) {
		this.descripcionCat = descripcionCat;
	}
	


}
