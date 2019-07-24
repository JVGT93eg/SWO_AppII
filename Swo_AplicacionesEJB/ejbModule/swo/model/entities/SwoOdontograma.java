package swo.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the swo_odontograma database table.
 * 
 */
@Entity
@Table(name="swo_odontograma")
@NamedQuery(name="SwoOdontograma.findAll", query="SELECT s FROM SwoOdontograma s")
public class SwoOdontograma implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_ate", unique=true, nullable=false)
	private Integer codigoAte;

	@Column(name="descripcion_ate", nullable=false, length=2147483647)
	private String descripcionAte;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_ate", nullable=false)
	private Date fechaAte;

	//bi-directional many-to-one association to SwoCara
	@ManyToOne
	@JoinColumn(name="codigo_car")
	private SwoCara swoCara;

	//bi-directional many-to-one association to SwoDiente
	@ManyToOne
	@JoinColumn(name="codigo_die")
	private SwoDiente swoDiente;

	//bi-directional many-to-one association to SwoPaciente
	@ManyToOne
	@JoinColumn(name="codigo_pac")
	private SwoPaciente swoPaciente;

	//bi-directional many-to-one association to SwoTratamiento
	@ManyToOne
	@JoinColumn(name="codigo_tra")
	private SwoTratamiento swoTratamiento;

	public SwoOdontograma() {
	}

	public Integer getCodigoAte() {
		return this.codigoAte;
	}

	public void setCodigoAte(Integer codigoAte) {
		this.codigoAte = codigoAte;
	}

	public String getDescripcionAte() {
		return this.descripcionAte;
	}

	public void setDescripcionAte(String descripcionAte) {
		this.descripcionAte = descripcionAte;
	}

	public Date getFechaAte() {
		return this.fechaAte;
	}

	public void setFechaAte(Date fechaAte) {
		this.fechaAte = fechaAte;
	}

	public SwoCara getSwoCara() {
		return this.swoCara;
	}

	public void setSwoCara(SwoCara swoCara) {
		this.swoCara = swoCara;
	}

	public SwoDiente getSwoDiente() {
		return this.swoDiente;
	}

	public void setSwoDiente(SwoDiente swoDiente) {
		this.swoDiente = swoDiente;
	}

	public SwoPaciente getSwoPaciente() {
		return this.swoPaciente;
	}

	public void setSwoPaciente(SwoPaciente swoPaciente) {
		this.swoPaciente = swoPaciente;
	}

	public SwoTratamiento getSwoTratamiento() {
		return this.swoTratamiento;
	}

	public void setSwoTratamiento(SwoTratamiento swoTratamiento) {
		this.swoTratamiento = swoTratamiento;
	}

}