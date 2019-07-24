package swo.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the swo_usuarios database table.
 * 
 */
@Entity
@Table(name="swo_usuarios")
@NamedQuery(name="SwoUsuario.findAll", query="SELECT s FROM SwoUsuario s")
public class SwoUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cedula_usu", unique=true, nullable=false, length=20)
	private String cedulaUsu;

	@Column(name="apellido_usu", nullable=false, length=50)
	private String apellidoUsu;

	@Column(name="clave_usu", nullable=false, length=20)
	private String claveUsu;

	@Column(name="direccion_usu", nullable=false, length=2147483647)
	private String direccionUsu;

	@Column(name="edad_usu", nullable=false, precision=2)
	private BigDecimal edadUsu;

	@Column(name="email_usu", nullable=false, length=50)
	private String emailUsu;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_naci_usu", nullable=false)
	private Date fechaNaciUsu;

	@Column(name="nombre_usu", nullable=false, length=50)
	private String nombreUsu;

	@Column(name="telefono_usu", length=20)
	private String telefonoUsu;

	//bi-directional many-to-one association to SwoEvento
	@OneToMany(mappedBy="swoUsuario")
	private List<SwoEvento> swoEventos;

	//bi-directional many-to-one association to SwoRole
	@ManyToOne
	@JoinColumn(name="codigo_rol_swo_roles")
	private SwoRole swoRole;

	public SwoUsuario() {
	}

	public String getCedulaUsu() {
		return this.cedulaUsu;
	}

	public void setCedulaUsu(String cedulaUsu) {
		this.cedulaUsu = cedulaUsu;
	}

	public String getApellidoUsu() {
		return this.apellidoUsu;
	}

	public void setApellidoUsu(String apellidoUsu) {
		this.apellidoUsu = apellidoUsu;
	}

	public String getClaveUsu() {
		return this.claveUsu;
	}

	public void setClaveUsu(String claveUsu) {
		this.claveUsu = claveUsu;
	}

	public String getDireccionUsu() {
		return this.direccionUsu;
	}

	public void setDireccionUsu(String direccionUsu) {
		this.direccionUsu = direccionUsu;
	}

	public BigDecimal getEdadUsu() {
		return this.edadUsu;
	}

	public void setEdadUsu(BigDecimal edadUsu) {
		this.edadUsu = edadUsu;
	}

	public String getEmailUsu() {
		return this.emailUsu;
	}

	public void setEmailUsu(String emailUsu) {
		this.emailUsu = emailUsu;
	}

	public Date getFechaNaciUsu() {
		return this.fechaNaciUsu;
	}

	public void setFechaNaciUsu(Date fechaNaciUsu) {
		this.fechaNaciUsu = fechaNaciUsu;
	}

	public String getNombreUsu() {
		return this.nombreUsu;
	}

	public void setNombreUsu(String nombreUsu) {
		this.nombreUsu = nombreUsu;
	}

	public String getTelefonoUsu() {
		return this.telefonoUsu;
	}

	public void setTelefonoUsu(String telefonoUsu) {
		this.telefonoUsu = telefonoUsu;
	}

	public List<SwoEvento> getSwoEventos() {
		return this.swoEventos;
	}

	public void setSwoEventos(List<SwoEvento> swoEventos) {
		this.swoEventos = swoEventos;
	}

	public SwoEvento addSwoEvento(SwoEvento swoEvento) {
		getSwoEventos().add(swoEvento);
		swoEvento.setSwoUsuario(this);

		return swoEvento;
	}

	public SwoEvento removeSwoEvento(SwoEvento swoEvento) {
		getSwoEventos().remove(swoEvento);
		swoEvento.setSwoUsuario(null);

		return swoEvento;
	}

	public SwoRole getSwoRole() {
		return this.swoRole;
	}

	public void setSwoRole(SwoRole swoRole) {
		this.swoRole = swoRole;
	}

}