package swo.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the swo_dientes database table.
 * 
 */
@Entity
@Table(name="swo_dientes")
@NamedQuery(name="SwoDiente.findAll", query="SELECT s FROM SwoDiente s")
public class SwoDiente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SWO_DIENTES_CODIGODIE_GENERATOR", sequenceName="SEQ_SWO_DIENTES", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SWO_DIENTES_CODIGODIE_GENERATOR")
	@Column(name="codigo_die", unique=true, nullable=false)
	private Integer codigoDie;

	@Column(name="descripcion_die", nullable=false, length=2147483647)
	private String descripcionDie;

	@Column(name="estado_die", nullable=false, length=10)
	private String estadoDie;

	@Column(name="nombre_die", nullable=false, length=50)
	private String nombreDie;

	@Column(name="numero_die", nullable=false)
	private Integer numeroDie;

	//bi-directional many-to-one association to SwoOdontograma
	@OneToMany(mappedBy="swoDiente")
	private List<SwoOdontograma> swoOdontogramas;

	public SwoDiente() {
	}

	public Integer getCodigoDie() {
		return this.codigoDie;
	}

	public void setCodigoDie(Integer codigoDie) {
		this.codigoDie = codigoDie;
	}

	public String getDescripcionDie() {
		return this.descripcionDie;
	}

	public void setDescripcionDie(String descripcionDie) {
		this.descripcionDie = descripcionDie;
	}

	public String getEstadoDie() {
		return this.estadoDie;
	}

	public void setEstadoDie(String estadoDie) {
		this.estadoDie = estadoDie;
	}

	public String getNombreDie() {
		return this.nombreDie;
	}

	public void setNombreDie(String nombreDie) {
		this.nombreDie = nombreDie;
	}

	public Integer getNumeroDie() {
		return this.numeroDie;
	}

	public void setNumeroDie(Integer numeroDie) {
		this.numeroDie = numeroDie;
	}

	public List<SwoOdontograma> getSwoOdontogramas() {
		return this.swoOdontogramas;
	}

	public void setSwoOdontogramas(List<SwoOdontograma> swoOdontogramas) {
		this.swoOdontogramas = swoOdontogramas;
	}

	public SwoOdontograma addSwoOdontograma(SwoOdontograma swoOdontograma) {
		getSwoOdontogramas().add(swoOdontograma);
		swoOdontograma.setSwoDiente(this);

		return swoOdontograma;
	}

	public SwoOdontograma removeSwoOdontograma(SwoOdontograma swoOdontograma) {
		getSwoOdontogramas().remove(swoOdontograma);
		swoOdontograma.setSwoDiente(null);

		return swoOdontograma;
	}

}