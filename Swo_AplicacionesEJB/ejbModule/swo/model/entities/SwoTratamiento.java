package swo.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the swo_tratamientos database table.
 * 
 */
@Entity
@Table(name="swo_tratamientos")
@NamedQuery(name="SwoTratamiento.findAll", query="SELECT s FROM SwoTratamiento s")
public class SwoTratamiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SWO_TRATAMIENTOS_CODIGOTRA_GENERATOR", sequenceName="SEQ_SWO_TRATAMIENTOS",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SWO_TRATAMIENTOS_CODIGOTRA_GENERATOR")
	@Column(name="codigo_tra", unique=true, nullable=false)
	private Integer codigoTra;

	@Column(name="descripcion_tra", nullable=false, length=2147483647)
	private String descripcionTra;

	@Column(name="estado_tra", nullable=false, length=20)
	private String estadoTra;

	@Column(name="precio_tra", nullable=false)
	private double precioTra;

	//bi-directional many-to-one association to SwoOdontograma
	@OneToMany(mappedBy="swoTratamiento")
	private List<SwoOdontograma> swoOdontogramas;

	//bi-directional many-to-one association to SwoProcedimiento
	@OneToMany(mappedBy="swoTratamiento")
	private List<SwoProcedimiento> swoProcedimientos;

	//bi-directional many-to-one association to SwoCategoria
	@ManyToOne
	@JoinColumn(name="codigo_cat")
	private SwoCategoria swoCategoria;

	public SwoTratamiento() {
	}

	public Integer getCodigoTra() {
		return this.codigoTra;
	}

	public void setCodigoTra(Integer codigoTra) {
		this.codigoTra = codigoTra;
	}

	public String getDescripcionTra() {
		return this.descripcionTra;
	}

	public void setDescripcionTra(String descripcionTra) {
		this.descripcionTra = descripcionTra;
	}

	public String getEstadoTra() {
		return this.estadoTra;
	}

	public void setEstadoTra(String estadoTra) {
		this.estadoTra = estadoTra;
	}

	public double getPrecioTra() {
		return this.precioTra;
	}

	public void setPrecioTra(double precioTra) {
		this.precioTra = precioTra;
	}

	public List<SwoOdontograma> getSwoOdontogramas() {
		return this.swoOdontogramas;
	}

	public void setSwoOdontogramas(List<SwoOdontograma> swoOdontogramas) {
		this.swoOdontogramas = swoOdontogramas;
	}

	public SwoOdontograma addSwoOdontograma(SwoOdontograma swoOdontograma) {
		getSwoOdontogramas().add(swoOdontograma);
		swoOdontograma.setSwoTratamiento(this);

		return swoOdontograma;
	}

	public SwoOdontograma removeSwoOdontograma(SwoOdontograma swoOdontograma) {
		getSwoOdontogramas().remove(swoOdontograma);
		swoOdontograma.setSwoTratamiento(null);

		return swoOdontograma;
	}

	public List<SwoProcedimiento> getSwoProcedimientos() {
		return this.swoProcedimientos;
	}

	public void setSwoProcedimientos(List<SwoProcedimiento> swoProcedimientos) {
		this.swoProcedimientos = swoProcedimientos;
	}

	public SwoProcedimiento addSwoProcedimiento(SwoProcedimiento swoProcedimiento) {
		getSwoProcedimientos().add(swoProcedimiento);
		swoProcedimiento.setSwoTratamiento(this);

		return swoProcedimiento;
	}

	public SwoProcedimiento removeSwoProcedimiento(SwoProcedimiento swoProcedimiento) {
		getSwoProcedimientos().remove(swoProcedimiento);
		swoProcedimiento.setSwoTratamiento(null);

		return swoProcedimiento;
	}

	public SwoCategoria getSwoCategoria() {
		return this.swoCategoria;
	}

	public void setSwoCategoria(SwoCategoria swoCategoria) {
		this.swoCategoria = swoCategoria;
	}

}