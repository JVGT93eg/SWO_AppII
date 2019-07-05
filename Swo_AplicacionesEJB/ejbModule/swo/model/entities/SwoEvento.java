package swo.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
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
	@SequenceGenerator(name="SWO_EVENTOS_CODIGOEVE_GENERATOR", sequenceName="SEQ_SWO_EVENTOS", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SWO_EVENTOS_CODIGOEVE_GENERATOR")
	@Column(name="codigo_eve", unique=true, nullable=false)
	private Integer codigoEve;

	@Column(name="descripcion_eve", nullable=false, length=2147483647)
	private String descripcionEve;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_eve", nullable=false)
	private Date fechaEve;

	@Column(name="hora_eve", nullable=false)
	private Time horaEve;

	@Column(name="ip_eve", nullable=false, length=100)
	private String ipEve;

	@Column(name="nombre_eve", nullable=false, length=50)
	private String nombreEve;

	//bi-directional many-to-one association to SwoLogin
	@ManyToOne
	@JoinColumn(name="codigo_login_swo_login")
	private SwoLogin swoLogin;

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

	public Time getHoraEve() {
		return this.horaEve;
	}

	public void setHoraEve(Time horaEve) {
		this.horaEve = horaEve;
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

	public SwoLogin getSwoLogin() {
		return this.swoLogin;
	}

	public void setSwoLogin(SwoLogin swoLogin) {
		this.swoLogin = swoLogin;
	}

}