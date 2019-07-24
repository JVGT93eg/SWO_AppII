package swo.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the swo_detalles_inventario database table.
 * 
 */
@Entity
@Table(name="swo_detalles_inventario")
@NamedQuery(name="SwoDetallesInventario.findAll", query="SELECT s FROM SwoDetallesInventario s")
public class SwoDetallesInventario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_det_inv", unique=true, nullable=false)
	private Integer codigoDetInv;

	@Column(name="cantidad_trans", nullable=false)
	private Integer cantidadTrans;

	@Column(name="descripcion_det_inv", nullable=false, length=2147483647)
	private String descripcionDetInv;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_trans", nullable=false)
	private Date fechaTrans;

	@Column(name="precio_trans", nullable=false)
	private double precioTrans;

	//bi-directional many-to-one association to SwoArticulo
	@ManyToOne
	@JoinColumn(name="codigo_art")
	private SwoArticulo swoArticulo;

	//bi-directional many-to-one association to SwoProveedore
	@ManyToOne
	@JoinColumn(name="codigo_prov")
	private SwoProveedore swoProveedore;

	//bi-directional many-to-one association to SwoTipoTransaccion
	@ManyToOne
	@JoinColumn(name="codigo_tipo_trans")
	private SwoTipoTransaccion swoTipoTransaccion;

	public SwoDetallesInventario() {
	}

	public Integer getCodigoDetInv() {
		return this.codigoDetInv;
	}

	public void setCodigoDetInv(Integer codigoDetInv) {
		this.codigoDetInv = codigoDetInv;
	}

	public Integer getCantidadTrans() {
		return this.cantidadTrans;
	}

	public void setCantidadTrans(Integer cantidadTrans) {
		this.cantidadTrans = cantidadTrans;
	}

	public String getDescripcionDetInv() {
		return this.descripcionDetInv;
	}

	public void setDescripcionDetInv(String descripcionDetInv) {
		this.descripcionDetInv = descripcionDetInv;
	}

	public Date getFechaTrans() {
		return this.fechaTrans;
	}

	public void setFechaTrans(Date fechaTrans) {
		this.fechaTrans = fechaTrans;
	}

	public double getPrecioTrans() {
		return this.precioTrans;
	}

	public void setPrecioTrans(double precioTrans) {
		this.precioTrans = precioTrans;
	}

	public SwoArticulo getSwoArticulo() {
		return this.swoArticulo;
	}

	public void setSwoArticulo(SwoArticulo swoArticulo) {
		this.swoArticulo = swoArticulo;
	}

	public SwoProveedore getSwoProveedore() {
		return this.swoProveedore;
	}

	public void setSwoProveedore(SwoProveedore swoProveedore) {
		this.swoProveedore = swoProveedore;
	}

	public SwoTipoTransaccion getSwoTipoTransaccion() {
		return this.swoTipoTransaccion;
	}

	public void setSwoTipoTransaccion(SwoTipoTransaccion swoTipoTransaccion) {
		this.swoTipoTransaccion = swoTipoTransaccion;
	}

}