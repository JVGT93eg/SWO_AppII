package swo.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import swo.model.entities.SwoProveedore;
import swo.model.manager.ManagerSwoProveedor;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanSwoProveedor implements Serializable {
	private static final long serialVersionUID = 1L;
	 @EJB
	 private ManagerSwoProveedor managerSwoProveedor;
	 private List<SwoProveedore> listaProveedores;
	 private SwoProveedore swoProveedor;
	 private boolean panelColapsado;
	 private SwoProveedore swoProveedorSeleccionado;
	 
	 @PostConstruct
	 public void inicializar() {
		 listaProveedores=managerSwoProveedor.findAllProveedores();
		 swoProveedor=new SwoProveedore();
		 panelColapsado = true;
	 }
	 public void actionListenerColapsarPanel() {
		 panelColapsado=!panelColapsado;
	 }
	 public void actionListenerInsertarProveedor() {
		 try {
			 managerSwoProveedor.insertProveedor(swoProveedor);
			 listaProveedores=managerSwoProveedor.findAllProveedores();
			 swoProveedor=new SwoProveedore();
			 JSFUtil.crearMensajeInfo("Proveedor Insertado");
		 }catch (Exception ex) {
			 JSFUtil.crearMensajeError("Error: "+ex.getMessage());
		 }
	 }
	 public void actionListenerEliminarSwoProveedor(int codigoProv) {
		 try {
			 managerSwoProveedor.deleteProveedor(codigoProv);
			 listaProveedores=managerSwoProveedor.findAllProveedores();
			 JSFUtil.crearMensajeWarning("Proveedor eliminado");
		 }catch (Exception ex) {
			 JSFUtil.crearMensajeError("Error: "+ex.getMessage());
		 }
	 }
	 public void actionListenerSeleccionarProveedor(SwoProveedore swoProveedors) {
		 swoProveedorSeleccionado=swoProveedors;
	 }
	 public void actionListenerActualizarSwoProveedor() {
		 try {
			 managerSwoProveedor.updateProveedor(swoProveedorSeleccionado);
			 listaProveedores=managerSwoProveedor.findAllProveedores();
			 JSFUtil.crearMensajeInfo("Proveedor Actualizado");
		 }catch (Exception ex) {
			 JSFUtil.crearMensajeError("Error: "+ex.getMessage());
		 }
	 }
	 public void actionListenerCrearMsjInf() {
			JSFUtil.crearMensajeInfo("Mensaje informativo");
		}
		
		public void actionListenerCrearMsjWarn() {
			JSFUtil.crearMensajeWarning("Mensaje de advertencia");
		}
		
		public void actionListenerCrearMsjError() {
			JSFUtil.crearMensajeError("Mensaje de error");
		}
		public List<SwoProveedore> getListaProveedores() {
			return listaProveedores;
		}
		public void setListaProveedores(List<SwoProveedore> listaProveedores) {
			this.listaProveedores = listaProveedores;
		}
		public SwoProveedore getSwoProveedor() {
			return swoProveedor;
		}
		public void setSwoProveedor(SwoProveedore swoProveedor) {
			this.swoProveedor = swoProveedor;
		}
		public boolean isPanelColapsado() {
			return panelColapsado;
		}
		public void setPanelColapsado(boolean panelColapsado) {
			this.panelColapsado = panelColapsado;
		}
		public SwoProveedore getSwoProveedorSeleccionado() {
			return swoProveedorSeleccionado;
		}
		public void setSwoProveedorSeleccionado(SwoProveedore swoProveedorSeleccionado) {
			this.swoProveedorSeleccionado = swoProveedorSeleccionado;
		}
	
}
