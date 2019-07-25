package swo.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the swo_eventos database table.
 * 
 */
@Entity
@Table(name="swo_eventos")
@NamedQuery(name="SwoEvento.findAll", query="SELECT s FROM SwoEvento s")
public class SwoEvento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_eve")
	private Integer codigoEve;

	@Column(name="descripcion_eve")
	private String descripcionEve;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_eve")
	private Date fechaEve;

	@Column(name="ip_eve")
	private String ipEve;

	@Column(name="nombre_eve")
	private String nombreEve;

	//bi-directional many-to-one association to SwoUsuario
	@ManyToOne
	@JoinColumn(name="cedula_usu_swo_usuarios")
	private SwoUsuario swoUsuario;

	public SwoEvento() {
	}

	public Integer getCodigoEve() {
		return this.codigoEve;
	}

	public void setCodigoEve(Integer codigoEve) {
		this.codigoEve = codigoEve;
	}

	public String getDescripcionEve() {
		return this.descripcionEve;
	}

	public void setDescripcionEve(String descripcionEve) {
		this.descripcionEve = descripcionEve;
	}

	public Date getFechaEve() {
		return this.fechaEve;
	}

	public void setFechaEve(Date fechaEve) {
		this.fechaEve = fechaEve;
	}

	public String getIpEve() {
		return this.ipEve;
	}

	public void setIpEve(String ipEve) {
		this.ipEve = ipEve;
	}

	public String getNombreEve() {
		return this.nombreEve;
	}

	public void setNombreEve(String nombreEve) {
		this.nombreEve = nombreEve;
	}

	public SwoUsuario getSwoUsuario() {
		return this.swoUsuario;
	}

	public void setSwoUsuario(SwoUsuario swoUsuario) {
		this.swoUsuario = swoUsuario;
	}

}