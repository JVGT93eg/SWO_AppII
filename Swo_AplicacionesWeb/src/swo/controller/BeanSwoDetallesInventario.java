package swo.controller;




import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import swo.model.entities.SwoArticulo;
import swo.model.entities.SwoDetallesInventario;
import swo.model.entities.SwoProveedore;
import swo.model.entities.SwoTipoTransaccion;
import swo.model.manager.ManagerArticulo;
import swo.model.manager.ManagerSwoDetallesInventario;
import swo.model.manager.ManagerSwoProveedor;
import swo.model.manager.ManagerSwoTipoTransaccion;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanSwoDetallesInventario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ManagerSwoDetallesInventario managerSwoDetalles;
	@EJB
	private ManagerArticulo managerSwoArticulo;
	@EJB
	private ManagerSwoProveedor managerProveedor;
	@EJB
	private ManagerSwoTipoTransaccion managerTipoTrans; 
	private List<SwoDetallesInventario> listaDetalles;
	private List<SwoTipoTransaccion> listaTipoTransaccion;
	private List<SwoArticulo> listaArticulo;
	private List<SwoProveedore> listaProveedores;
	private SwoDetallesInventario swoDetalles;
	private boolean panelColapsado;
	private SwoDetallesInventario swoDetallesSeleccionado;
	private int codigoArt;
	private int codigoProv;
	private int codigoTipoTrans;
	
	@PostConstruct
	public void inicalizar(){
		listaDetalles = managerSwoDetalles.findAllDetallesInventario();
		listaArticulo=managerSwoArticulo.listarArticulo();
		listaProveedores=managerProveedor.findAllProveedores();
		listaTipoTransaccion=managerTipoTrans.findAllTipoTransaccion();
		swoDetalles=new SwoDetallesInventario();
		panelColapsado =true;
	}
	public void actionListenerColapsarPanel() {
		panelColapsado=!panelColapsado;
	}
	public void actionListenerInsertarSwoDetalles() {
		try {
			managerSwoDetalles.insertDetalleInventario(swoDetalles, codigoArt, codigoProv, codigoTipoTrans);
			listaDetalles = managerSwoDetalles.findAllDetallesInventario();
			swoDetalles = new SwoDetallesInventario();
			JSFUtil.crearMensajeInfo("Detalle del Inventario Registrado");
		}catch (Exception ex) {
			JSFUtil.crearMensajeError("Error: "+ex.getMessage());
			ex.printStackTrace();
		}
	}
	public void actionListenerEliminarDetalles(int codigoDetInv) {
		try {
			managerSwoDetalles.deleteDetalleInventario(codigoDetInv);
			listaDetalles=managerSwoDetalles.findAllDetallesInventario();
			JSFUtil.crearMensajeWarning("Detalle del inventario Eliminado!!");
		}catch(Exception ex) {
			JSFUtil.crearMensajeError("Error: "+ex.getMessage());
		}
	}
	public void actionListenerSeleccionarDetalle(SwoDetallesInventario swoDetalle) {
		swoDetallesSeleccionado=swoDetalles;
	}
	public void actionListenerActualizarDetalles() {
		try {
			managerSwoDetalles.updateDetalleInventario(swoDetallesSeleccionado, codigoArt, codigoProv, codigoTipoTrans);
			listaDetalles=managerSwoDetalles.findAllDetallesInventario();
			JSFUtil.crearMensajeInfo("Datos Actualizados Correctamente");
		}catch (Exception ex) {
			JSFUtil.crearMensajeError("Error: "+ex.getMessage());
		}
	}
	public void actionListenerCrearMsjInf() {
		JSFUtil.crearMensajeInfo("Mensaje Informativo");
	}
	public void actionListenerCrearMsjWarn() {
		JSFUtil.crearMensajeWarning("Mensaje de Advertencia");
	}
	public void actionListenerCrearMsjError() {
		JSFUtil.crearMensajeError("Mensaje de Error");
	}
	public List<SwoDetallesInventario> getListaDetalles() {
		return listaDetalles;
	}
	public void setListaDetalles(List<SwoDetallesInventario> listaDetalles) {
		this.listaDetalles = listaDetalles;
	}
	public SwoDetallesInventario getSwoDetalles() {
		return swoDetalles;
	}
	public void setSwoDetalles(SwoDetallesInventario swoDetalles) {
		this.swoDetalles = swoDetalles;
	}
	public boolean isPanelColapsado() {
		return panelColapsado;
	}
	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}
	public SwoDetallesInventario getSwoDetallesSeleccionado() {
		return swoDetallesSeleccionado;
	}
	public void setSwoDetallesSeleccionado(SwoDetallesInventario swoDetallesSeleccionado) {
		this.swoDetallesSeleccionado = swoDetallesSeleccionado;
	}
	public int getCodigoArt() {
		return codigoArt;
	}
	public void setCodigoArt(int codigoArt) {
		this.codigoArt = codigoArt;
	}
	public int getCodigoProv() {
		return codigoProv;
	}
	public void setCodigoProv(int codigoProv) {
		this.codigoProv = codigoProv;
	}
	public int getCodigoTipoTrans() {
		return codigoTipoTrans;
	}
	public void setCodigoTipoTrans(int codigoTipoTrans) {
		this.codigoTipoTrans = codigoTipoTrans;
	}
	public List<SwoTipoTransaccion> getListaTipoTransaccion() {
		return listaTipoTransaccion;
	}
	public void setListaTipoTransaccion(List<SwoTipoTransaccion> listaTipoTransaccion) {
		this.listaTipoTransaccion = listaTipoTransaccion;
	}
	public List<SwoArticulo> getListaArticulo() {
		return listaArticulo;
	}
	public void setListaArticulo(List<SwoArticulo> listaArticulo) {
		this.listaArticulo = listaArticulo;
	}
	public List<SwoProveedore> getListaProveedores() {
		return listaProveedores;
	}
	public void setListaProveedores(List<SwoProveedore> listaProveedores) {
		this.listaProveedores = listaProveedores;
	}
	
}
