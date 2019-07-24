package swo.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import swo.model.entities.SwoArticulo;
import swo.model.entities.SwoOdontograma;
import swo.model.entities.SwoPaciente;
import swo.model.entities.SwoTratamiento;
import swo.model.manager.ManagerArticulo;
import swo.model.manager.ManagerOdontograma;
import swo.model.manager.ManagerTratamiento;
import swo.model.manager.MangerPaciente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
@SessionScoped
public class BeanOdontograma implements Serializable {

	private static final long serialVersionUID = 1L;
	private int codigoPac;
	@EJB
	private ManagerOdontograma managerOdontograma;
	@EJB
	private MangerPaciente managerPaciente;
	@EJB
	private ManagerTratamiento managerTratamiento;
	private List<SwoOdontograma> listaOdontograma;
	private List<SwoPaciente> listaPaciente;
	private SwoPaciente paciente;
	private SwoOdontograma odontograma;
	private boolean panelColapsado;
	private SwoPaciente pacienteSelecionado;
	private SwoOdontograma OdontogramaCabTmp;
	private ManagerArticulo managerArticulo;
	private boolean OdontogramaCabTmpGuardada;
	
	
	
	//datos para insertar las claves foraneás
	private String descripcion_ate;
	private Date fecha_ate;
	private int codtrata;
	private int coddie;
	private int codcar;
	private int codpac;
	
	private Integer codCategoria;
	private Integer codArticulo;
	private Integer cantidad;
	private Integer costo;

	@PostConstruct
   public void inicializar() {
	listaOdontograma =managerOdontograma.findAllOdontograma();
	listaPaciente = managerOdontograma.listarPacie();
	odontograma=new SwoOdontograma();
	paciente=new SwoPaciente();
	panelColapsado=true;
	}
	
	/**
	 * Action para la creacion de una factura temporal en memoria.
	 * Hace uso del componente {@link facturacion.model.manager.ManagerFacturacion ManagerFacturacion} de la capa model.
	 * @return outcome para la navegacion.
	 */
	public String crearNuevoOdontograma(){
		OdontogramaCabTmp=managerOdontograma.crearOdontogramaTmp();
		codigoPac=0;
		codtrata=0;
		coddie=0;
		codcar=0;
		OdontogramaCabTmpGuardada=false;
		return "";
	}
	/**
	 * Action para asignar un cliente a la factura temporal actual.
	 * Hace uso del componente {@link facturacion.model.manager.ManagerFacturacion ManagerFacturacion} de la capa model.
	 * @return outcome para la navegacion.
	 */
	public void asignarPaciente(){
		if(OdontogramaCabTmpGuardada==true){
			JSFUtil.crearMensajeWarning("El Odontograma ya fue guardado.");
		}
		try {
			managerOdontograma.asignarPacienteOdontogramaTemp(OdontogramaCabTmp, codigoPac);
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}
	/**
	 * Devuelve un listado de componentes SelectItem a partir
	 * de un listado de {@link swo.model.dao.entities.SwoPaciente SwoPaciente}.
	 * @return listado de SelectItems de pacientes.
	 */
	
	public List<SelectItem> getListaPAciente_SI(){
		List<SelectItem> listadoSI=new ArrayList<SelectItem>();
		List<SwoPaciente> listadoPacientes= managerPaciente.findAllPacientesDao();
		
		for(SwoPaciente c:listadoPacientes) {
			SelectItem item=new SelectItem(c.getCodigoPac(),
			c.getApellidoPac()+"  "+c.getNombrePac()		);
			listadoSI.add(item);
		}
		
		return listadoSI;
	}
	
	public List<SelectItem> getListaTratamientoSI(){
		List<SelectItem>  listadoSI=new ArrayList<SelectItem>();
		List<SwoTratamiento> listaTratamientos=managerTratamiento.findAll_Tratamiento();
		
		for(SwoTratamiento t:listaTratamientos){
			SelectItem item=new SelectItem(t.getCodigoTra(), 
					                   t.getDescripcionTra());
			listadoSI.add(item);
		}
		return listadoSI;
		
		
	}
	
//	public List<SelectItem> getlistaArticulosSI(){
//		
//	}
	
	
	public List<SelectItem> getListaArticulosSI(){
		List<SelectItem>  listadoSI=new ArrayList<SelectItem>();
		List<SwoArticulo> listaArticulos=managerArticulo.findAll_Articulos();
		
		for(SwoArticulo t:listaArticulos){
			SelectItem item=new SelectItem(t.getCodigoArt(), 
				                   t.getDescripcionArt());
		listadoSI.add(item);
		}
		return listadoSI;
		
		
	}
	
	public void verificarExistencia(){
		try {
				JSFUtil.crearMensajeError("No hay existencia");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Action que adiciona un item a una factura temporal.
	 * Hace uso del componente {@link model.manager.ManagerFacturacion ManagerFacturacion} de la capa model.
	 * @return
	 */
	public String insertarDetalleOdonto(){
		if(OdontogramaCabTmpGuardada==true){
			JSFUtil.crearMensajeWarning("El Odontograma ya fue guardado.");
			return "";
		}
		try {
			managerOdontograma.agregarDetalleOdontogramaTmp(OdontogramaCabTmp,codCategoria,codArticulo,cantidad,costo);
			codCategoria=0;
			codArticulo=0;
			cantidad=0;
			costo=0;
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}		
		return "";
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
	public SwoOdontograma getOdontogramaCabTmp() {
		return OdontogramaCabTmp;
	}
	public void setOdontogramaCabTmp(SwoOdontograma odontogramaCabTmp) {
		OdontogramaCabTmp = odontogramaCabTmp;
	}
	public boolean isOdontogramaCabTmpGuardada() {
		return OdontogramaCabTmpGuardada;
	}
	public void setOdontogramaCabTmpGuardada(boolean odontogramaCabTmpGuardada) {
		OdontogramaCabTmpGuardada = odontogramaCabTmpGuardada;
	}

	public int getCodigoPac() {
		return codigoPac;
	}

	public void setCodigoPac(int codigoPac) {
		this.codigoPac = codigoPac;
	}

	public Integer getCodCategoria() {
		return codCategoria;
	}

	public void setCodCategoria(Integer codCategoria) {
		this.codCategoria = codCategoria;
	}

	public Integer getCodArticulo() {
		return codArticulo;
	}

	public void setCodArticulo(Integer codArticulo) {
		this.codArticulo = codArticulo;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getCosto() {
		return costo;
	}

	public void setCosto(Integer costo) {
		this.costo = costo;
	}
	
	
	
	
	
}
