package swo.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the swo_roles database table.
 * 
 */
@Entity
@Table(name="swo_roles")
@NamedQuery(name="SwoRole.findAll", query="SELECT s FROM SwoRole s")
public class SwoRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SWO_ROLES_CODIGOROL_GENERATOR", sequenceName="SEQ_SWO_ROLES", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SWO_ROLES_CODIGOROL_GENERATOR")
	@Column(name="codigo_rol", unique=true, nullable=false)
	private Integer codigoRol;

	@Column(name="descripcion_rol", nullable=false, length=2147483647)
	private String descripcionRol;

	@Column(name="nombre_rol", nullable=false, length=50)
	private String nombreRol;

	//bi-directional many-to-one association to SwoUsuario
	@OneToMany(mappedBy="swoRole")
	private List<SwoUsuario> swoUsuarios;

	public SwoRole() {
	}

	public Integer getCodigoRol() {
		return this.codigoRol;
	}

	public void setCodigoRol(Integer codigoRol) {
		this.codigoRol = codigoRol;
	}

	public String getDescripcionRol() {
		return this.descripcionRol;
	}

	public void setDescripcionRol(String descripcionRol) {
		this.descripcionRol = descripcionRol;
	}

	public String getNombreRol() {
		return this.nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	public List<SwoUsuario> getSwoUsuarios() {
		return this.swoUsuarios;
	}

	public void setSwoUsuarios(List<SwoUsuario> swoUsuarios) {
		this.swoUsuarios = swoUsuarios;
	}

	public SwoUsuario addSwoUsuario(SwoUsuario swoUsuario) {
		getSwoUsuarios().add(swoUsuario);
		swoUsuario.setSwoRole(this);

		return swoUsuario;
	}

	public SwoUsuario removeSwoUsuario(SwoUsuario swoUsuario) {
		getSwoUsuarios().remove(swoUsuario);
		swoUsuario.setSwoRole(null);

		return swoUsuario;
	}

}