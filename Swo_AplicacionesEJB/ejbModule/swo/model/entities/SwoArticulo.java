package swo.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the swo_articulos database table.
 * 
 */
@Entity
@Table(name="swo_articulos")
@NamedQuery(name="SwoArticulo.findAll", query="SELECT s FROM SwoArticulo s")
public class SwoArticulo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SWO_ARTICULOS_CODIGOART_GENERATOR", sequenceName="SEQ_SWO_ARTICULOS", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SWO_ARTICULOS_CODIGOART_GENERATOR")
	@Column(name="codigo_art", unique=true, nullable=false)
	private Integer codigoArt;

	@Column(name="descripcion_art", nullable=false, length=2147483647)
	private String descripcionArt;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_expi_art")
	private Date fechaExpiArt;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_fabr_art", nullable=false)
	private Date fechaFabrArt;

	@Column(name="lote_art", length=50)
	private String loteArt;

	@Column(name="marca_art", nullable=false, length=20)
	private String marcaArt;

	@Column(name="max_art", nullable=false)
	private Integer maxArt;

	@Column(name="min_art", nullable=false)
	private Integer minArt;

	@Column(name="nombre_art", nullable=false, length=50)
	private String nombreArt;

	@Column(name="riesgo_art", length=20)
	private String riesgoArt;

	@Column(name="stock_art", nullable=false)
	private Integer stockArt;

	//bi-directional many-to-one association to SwoDetallesInventario
	@OneToMany(mappedBy="swoArticulo")
	private List<SwoDetallesInventario> swoDetallesInventarios;

	//bi-directional many-to-one association to SwoProceArticulo
	@OneToMany(mappedBy="swoArticulo")
	private List<SwoProceArticulo> swoProceArticulos;

	public SwoArticulo() {
	}

	public Integer getCodigoArt() {
		return this.codigoArt;
	}

	public void setCodigoArt(Integer codigoArt) {
		this.codigoArt = codigoArt;
	}

	public String getDescripcionArt() {
		return this.descripcionArt;
	}

	public void setDescripcionArt(String descripcionArt) {
		this.descripcionArt = descripcionArt;
	}

	public Date getFechaExpiArt() {
		return this.fechaExpiArt;
	}

	public void setFechaExpiArt(Date fechaExpiArt) {
		this.fechaExpiArt = fechaExpiArt;
	}

	public Date getFechaFabrArt() {
		return this.fechaFabrArt;
	}

	public void setFechaFabrArt(Date fechaFabrArt) {
		this.fechaFabrArt = fechaFabrArt;
	}

	public String getLoteArt() {
		return this.loteArt;
	}

	public void setLoteArt(String loteArt) {
		this.loteArt = loteArt;
	}

	public String getMarcaArt() {
		return this.marcaArt;
	}

	public void setMarcaArt(String marcaArt) {
		this.marcaArt = marcaArt;
	}

	public Integer getMaxArt() {
		return this.maxArt;
	}

	public void setMaxArt(Integer maxArt) {
		this.maxArt = maxArt;
	}

	public Integer getMinArt() {
		return this.minArt;
	}

	public void setMinArt(Integer minArt) {
		this.minArt = minArt;
	}

	public String getNombreArt() {
		return this.nombreArt;
	}

	public void setNombreArt(String nombreArt) {
		this.nombreArt = nombreArt;
	}

	public String getRiesgoArt() {
		return this.riesgoArt;
	}

	public void setRiesgoArt(String riesgoArt) {
		this.riesgoArt = riesgoArt;
	}

	public Integer getStockArt() {
		return this.stockArt;
	}

	public void setStockArt(Integer stockArt) {
		this.stockArt = stockArt;
	}

	public List<SwoDetallesInventario> getSwoDetallesInventarios() {
		return this.swoDetallesInventarios;
	}

	public void setSwoDetallesInventarios(List<SwoDetallesInventario> swoDetallesInventarios) {
		this.swoDetallesInventarios = swoDetallesInventarios;
	}

	public SwoDetallesInventario addSwoDetallesInventario(SwoDetallesInventario swoDetallesInventario) {
		getSwoDetallesInventarios().add(swoDetallesInventario);
		swoDetallesInventario.setSwoArticulo(this);

		return swoDetallesInventario;
	}

	public SwoDetallesInventario removeSwoDetallesInventario(SwoDetallesInventario swoDetallesInventario) {
		getSwoDetallesInventarios().remove(swoDetallesInventario);
		swoDetallesInventario.setSwoArticulo(null);

		return swoDetallesInventario;
	}

	public List<SwoProceArticulo> getSwoProceArticulos() {
		return this.swoProceArticulos;
	}

	public void setSwoProceArticulos(List<SwoProceArticulo> swoProceArticulos) {
		this.swoProceArticulos = swoProceArticulos;
	}

	public SwoProceArticulo addSwoProceArticulo(SwoProceArticulo swoProceArticulo) {
		getSwoProceArticulos().add(swoProceArticulo);
		swoProceArticulo.setSwoArticulo(this);

		return swoProceArticulo;
	}

	public SwoProceArticulo removeSwoProceArticulo(SwoProceArticulo swoProceArticulo) {
		getSwoProceArticulos().remove(swoProceArticulo);
		swoProceArticulo.setSwoArticulo(null);

		return swoProceArticulo;
	}

}