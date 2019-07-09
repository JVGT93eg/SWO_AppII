package swo.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import swo.model.entities.SwoArticulo;
import swo.model.entities.SwoProceArticulo;
import swo.model.entities.SwoProcedimiento;
import swo.model.manager.ManagerProceArticulo;




@Named
@SessionScoped
public class BeanProceArticulo implements Serializable{
	private static final  long serialVersionUID = 1L;
	
	@EJB
	private ManagerProceArticulo managerProceArticulo;
	private List<SwoProceArticulo> lista;
	private List<SwoArticulo>listaA;
	private List<SwoProcedimiento>listaP;
	private SwoProceArticulo proceArticulo;
    private boolean panelColapso;
    private SwoProceArticulo proceArticuloSeleccionado;
    
  
    private int codArticulo;
    private int codProcedimiento;
    private SwoArticulo articulo;
    private SwoProcedimiento procedimiento;
    
    
    
    @PostConstruct
    public void inicializar() {
    	lista = managerProceArticulo.listarProceArticulo();
    	listaA = managerProceArticulo.listarArticulo();
    	listaP = managerProceArticulo.listarProcedimiento();
    	proceArticulo = new SwoProceArticulo();
    	articulo = new SwoArticulo();
    	procedimiento = new SwoProcedimiento();
    	panelColapso = true;
    }
    
    public void actionListenerColapsarPanel() {
		panelColapso=!panelColapso;
	}
    public void actionListenerInsertarProceArticulo() {
    	managerProceArticulo.insertarProceArticulo(codArticulo, codProcedimiento);
    	lista = managerProceArticulo.listarProceArticulo();
    	
    }
     
	public void actionListenerEliminarProceArticulo(int codigoProArt) {
		managerProceArticulo.eliminarProcedimiento(codigoProArt);
		lista = managerProceArticulo.listarProceArticulo();
		JSFUtil.crearMensajeInfo("Procedimiento con Articulo Eliminado");
	}
	
	public void actionListenerSeleccionarProceArticulo(SwoProceArticulo proceArticulo) {
		proceArticuloSeleccionado=proceArticulo;
	}
	
	public void actionListenerActualizarProceArticulo() {
		try {
			managerProceArticulo.actualizarProceArticulo(proceArticuloSeleccionado);
			lista = managerProceArticulo.listarProceArticulo();
			JSFUtil.crearMensajeInfo("Datos Actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	/////////////////////////////////////////////////////////////////////////

	public List<SwoProceArticulo> getLista() {
		return lista;
	}

	public void setLista(List<SwoProceArticulo> lista) {
		this.lista = lista;
	}

	public List<SwoArticulo> getListaA() {
		return listaA;
	}

	public void setListaA(List<SwoArticulo> listaA) {
		this.listaA = listaA;
	}

	public List<SwoProcedimiento> getListaP() {
		return listaP;
	}

	public void setListaP(List<SwoProcedimiento> listaP) {
		this.listaP = listaP;
	}

	public SwoProceArticulo getProceArticulo() {
		return proceArticulo;
	}

	public void setProceArticulo(SwoProceArticulo proceArticulo) {
		this.proceArticulo = proceArticulo;
	}

	public boolean isPanelColapso() {
		return panelColapso;
	}

	public void setPanelColapso(boolean panelColapso) {
		this.panelColapso = panelColapso;
	}

	public SwoProceArticulo getProceArticuloSeleccionado() {
		return proceArticuloSeleccionado;
	}

	public void setProceArticuloSeleccionado(SwoProceArticulo proceArticuloSeleccionado) {
		this.proceArticuloSeleccionado = proceArticuloSeleccionado;
	}

	public int getCodArticulo() {
		return codArticulo;
	}

	public void setCodArticulo(int codArticulo) {
		this.codArticulo = codArticulo;
	}

	public int getCodProcedimiento() {
		return codProcedimiento;
	}

	public void setCodProcedimiento(int codProcedimiento) {
		this.codProcedimiento = codProcedimiento;
	}

	public SwoArticulo getArticulo() {
		return articulo;
	}

	public void setArticulo(SwoArticulo articulo) {
		this.articulo = articulo;
	}

	public SwoProcedimiento getProcedimiento() {
		return procedimiento;
	}

	public void setProcedimiento(SwoProcedimiento procedimiento) {
		this.procedimiento = procedimiento;
	}
	
	
	//////////////////////////////////////////////////////////////////////////
	
	
}