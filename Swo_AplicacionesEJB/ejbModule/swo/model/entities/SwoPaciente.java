package swo.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the swo_pacientes database table.
 * 
 */
@Entity
@Table(name="swo_pacientes")
@NamedQuery(name="SwoPaciente.findAll", query="SELECT s FROM SwoPaciente s")
public class SwoPaciente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_pac", unique=true, nullable=false)
	private Integer codigoPac;

	@Column(name="apellido_pac", nullable=false, length=50)
	private String apellidoPac;

	@Column(name="cedula_pac", nullable=false, length=20)
	private String cedulaPac;

	@Column(name="direccion_pac", nullable=false, length=2147483647)
	private String direccionPac;

	@Column(name="email_pac", length=50)
	private String emailPac;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_naci_pac", nullable=false)
	private Date fechaNaciPac;

	@Column(name="nombre_pac", nullable=false, length=50)
	private String nombrePac;

	@Column(name="telefono_pac", length=20)
	private String telefonoPac;

	//bi-directional many-to-one association to SwoOdontograma
	@OneToMany(mappedBy="swoPaciente")
	private List<SwoOdontograma> swoOdontogramas;

	//bi-directional many-to-one association to SwoGenero
	@ManyToOne
	@JoinColumn(name="codigo_gen_swo_generos")
	private SwoGenero swoGenero;

	public SwoPaciente() {
	}

	public Integer getCodigoPac() {
		return this.codigoPac;
	}

	public void setCodigoPac(Integer codigoPac) {
		this.codigoPac = codigoPac;
	}

	public String getApellidoPac() {
		return this.apellidoPac;
	}

	public void setApellidoPac(String apellidoPac) {
		this.apellidoPac = apellidoPac;
	}

	public String getCedulaPac() {
		return this.cedulaPac;
	}

	public void setCedulaPac(String cedulaPac) {
		this.cedulaPac = cedulaPac;
	}

	public String getDireccionPac() {
		return this.direccionPac;
	}

	public void setDireccionPac(String direccionPac) {
		this.direccionPac = direccionPac;
	}

	public String getEmailPac() {
		return this.emailPac;
	}

	public void setEmailPac(String emailPac) {
		this.emailPac = emailPac;
	}

	public Date getFechaNaciPac() {
		return this.fechaNaciPac;
	}

	public void setFechaNaciPac(Date fechaNaciPac) {
		this.fechaNaciPac = fechaNaciPac;
	}

	public String getNombrePac() {
		return this.nombrePac;
	}

	public void setNombrePac(String nombrePac) {
		this.nombrePac = nombrePac;
	}

	public String getTelefonoPac() {
		return this.telefonoPac;
	}

	public void setTelefonoPac(String telefonoPac) {
		this.telefonoPac = telefonoPac;
	}

	public List<SwoOdontograma> getSwoOdontogramas() {
		return this.swoOdontogramas;
	}

	public void setSwoOdontogramas(List<SwoOdontograma> swoOdontogramas) {
		this.swoOdontogramas = swoOdontogramas;
	}

	public SwoOdontograma addSwoOdontograma(SwoOdontograma swoOdontograma) {
		getSwoOdontogramas().add(swoOdontograma);
		swoOdontograma.setSwoPaciente(this);

		return swoOdontograma;
	}

	public SwoOdontograma removeSwoOdontograma(SwoOdontograma swoOdontograma) {
		getSwoOdontogramas().remove(swoOdontograma);
		swoOdontograma.setSwoPaciente(null);

		return swoOdontograma;
	}

	public SwoGenero getSwoGenero() {
		return this.swoGenero;
	}

	public void setSwoGenero(SwoGenero swoGenero) {
		this.swoGenero = swoGenero;
	}

}