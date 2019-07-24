package swo.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the swo_proveedores database table.
 * 
 */
@Entity
@Table(name="swo_proveedores")
@NamedQuery(name="SwoProveedore.findAll", query="SELECT s FROM SwoProveedore s")
public class SwoProveedore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_prov", unique=true, nullable=false)
	private Integer codigoProv;

	@Column(name="cuidad_prov", nullable=false, length=50)
	private String cuidadProv;

	@Column(name="descripcion_prov", nullable=false, length=2147483647)
	private String descripcionProv;

	@Column(name="direccion_prov", nullable=false, length=2147483647)
	private String direccionProv;

	@Column(name="email_prov", nullable=false, length=50)
	private String emailProv;

	@Column(name="nombre_prov", nullable=false, length=50)
	private String nombreProv;

	@Column(name="telefono_prov", length=20)
	private String telefonoProv;

	//bi-directional many-to-one association to SwoDetallesInventario
	@OneToMany(mappedBy="swoProveedore")
	private List<SwoDetallesInventario> swoDetallesInventarios;

	public SwoProveedore() {
	}

	public Integer getCodigoProv() {
		return this.codigoProv;
	}

	public void setCodigoProv(Integer codigoProv) {
		this.codigoProv = codigoProv;
	}

	public String getCuidadProv() {
		return this.cuidadProv;
	}

	public void setCuidadProv(String cuidadProv) {
		this.cuidadProv = cuidadProv;
	}

	public String getDescripcionProv() {
		return this.descripcionProv;
	}

	public void setDescripcionProv(String descripcionProv) {
		this.descripcionProv = descripcionProv;
	}

	public String getDireccionProv() {
		return this.direccionProv;
	}

	public void setDireccionProv(String direccionProv) {
		this.direccionProv = direccionProv;
	}

	public String getEmailProv() {
		return this.emailProv;
	}

	public void setEmailProv(String emailProv) {
		this.emailProv = emailProv;
	}

	public String getNombreProv() {
		return this.nombreProv;
	}

	public void setNombreProv(String nombreProv) {
		this.nombreProv = nombreProv;
	}

	public String getTelefonoProv() {
		return this.telefonoProv;
	}

	public void setTelefonoProv(String telefonoProv) {
		this.telefonoProv = telefonoProv;
	}

	public List<SwoDetallesInventario> getSwoDetallesInventarios() {
		return this.swoDetallesInventarios;
	}

	public void setSwoDetallesInventarios(List<SwoDetallesInventario> swoDetallesInventarios) {
		this.swoDetallesInventarios = swoDetallesInventarios;
	}

	public SwoDetallesInventario addSwoDetallesInventario(SwoDetallesInventario swoDetallesInventario) {
		getSwoDetallesInventarios().add(swoDetallesInventario);
		swoDetallesInventario.setSwoProveedore(this);

		return swoDetallesInventario;
	}

	public SwoDetallesInventario removeSwoDetallesInventario(SwoDetallesInventario swoDetallesInventario) {
		getSwoDetallesInventarios().remove(swoDetallesInventario);
		swoDetallesInventario.setSwoProveedore(null);

		return swoDetallesInventario;
	}

}