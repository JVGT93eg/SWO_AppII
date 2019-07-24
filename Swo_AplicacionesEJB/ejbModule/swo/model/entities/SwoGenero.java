package swo.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the swo_generos database table.
 * 
 */
@Entity
@Table(name="swo_generos")
@NamedQuery(name="SwoGenero.findAll", query="SELECT s FROM SwoGenero s")
public class SwoGenero implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_gen", unique=true, nullable=false)
	private Integer codigoGen;

	@Column(name="descripcion_gen", nullable=false, length=2147483647)
	private String descripcionGen;

	//bi-directional many-to-one association to SwoPaciente
	@OneToMany(mappedBy="swoGenero")
	private List<SwoPaciente> swoPacientes;

	public SwoGenero() {
	}

	public Integer getCodigoGen() {
		return this.codigoGen;
	}

	public void setCodigoGen(Integer codigoGen) {
		this.codigoGen = codigoGen;
	}

	public String getDescripcionGen() {
		return this.descripcionGen;
	}

	public void setDescripcionGen(String descripcionGen) {
		this.descripcionGen = descripcionGen;
	}

	public List<SwoPaciente> getSwoPacientes() {
		return this.swoPacientes;
	}

	public void setSwoPacientes(List<SwoPaciente> swoPacientes) {
		this.swoPacientes = swoPacientes;
	}

	public SwoPaciente addSwoPaciente(SwoPaciente swoPaciente) {
		getSwoPacientes().add(swoPaciente);
		swoPaciente.setSwoGenero(this);

		return swoPaciente;
	}

	public SwoPaciente removeSwoPaciente(SwoPaciente swoPaciente) {
		getSwoPacientes().remove(swoPaciente);
		swoPaciente.setSwoGenero(null);

		return swoPaciente;
	}

}