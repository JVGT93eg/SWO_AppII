package swo.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the swo_login database table.
 * 
 */
@Entity
@Table(name="swo_login")
@NamedQuery(name="SwoLogin.findAll", query="SELECT s FROM SwoLogin s")
public class SwoLogin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SWO_LOGIN_CODIGOLOGIN_GENERATOR", sequenceName="SEQ_SWO_LOGIN", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SWO_LOGIN_CODIGOLOGIN_GENERATOR")
	@Column(name="codigo_login", unique=true, nullable=false)
	private Integer codigoLogin;

	//bi-directional many-to-one association to SwoEvento
	@OneToMany(mappedBy="swoLogin")
	private List<SwoEvento> swoEventos;

	//bi-directional many-to-one association to SwoRole
	@ManyToOne
	@JoinColumn(name="codigo_rol")
	private SwoRole swoRole;

	//bi-directional many-to-one association to SwoUsuario
	@ManyToOne
	@JoinColumn(name="codigo_usu")
	private SwoUsuario swoUsuario;

	public SwoLogin() {
	}

	public Integer getCodigoLogin() {
		return this.codigoLogin;
	}

	public void setCodigoLogin(Integer codigoLogin) {
		this.codigoLogin = codigoLogin;
	}

	public List<SwoEvento> getSwoEventos() {
		return this.swoEventos;
	}

	public void setSwoEventos(List<SwoEvento> swoEventos) {
		this.swoEventos = swoEventos;
	}

	public SwoEvento addSwoEvento(SwoEvento swoEvento) {
		getSwoEventos().add(swoEvento);
		swoEvento.setSwoLogin(this);

		return swoEvento;
	}

	public SwoEvento removeSwoEvento(SwoEvento swoEvento) {
		getSwoEventos().remove(swoEvento);
		swoEvento.setSwoLogin(null);

		return swoEvento;
	}

	public SwoRole getSwoRole() {
		return this.swoRole;
	}

	public void setSwoRole(SwoRole swoRole) {
		this.swoRole = swoRole;
	}

	public SwoUsuario getSwoUsuario() {
		return this.swoUsuario;
	}

	public void setSwoUsuario(SwoUsuario swoUsuario) {
		this.swoUsuario = swoUsuario;
	}

}