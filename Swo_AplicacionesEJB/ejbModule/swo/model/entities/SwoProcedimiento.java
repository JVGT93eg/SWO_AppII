package swo.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the swo_procedimientos database table.
 * 
 */
@Entity
@Table(name="swo_procedimientos")
@NamedQuery(name="SwoProcedimiento.findAll", query="SELECT s FROM SwoProcedimiento s")
public class SwoProcedimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_pro", unique=true, nullable=false)
	private Integer codigoPro;

	@Column(name="cantidad_proc", nullable=false)
	private Integer cantidadProc;

	@Column(name="descripcion_pro", nullable=false, length=2147483647)
	private String descripcionPro;

	//bi-directional many-to-one association to SwoProceArticulo
	@OneToMany(mappedBy="swoProcedimiento")
	private List<SwoProceArticulo> swoProceArticulos;

	//bi-directional many-to-one association to SwoTratamiento
	@ManyToOne
	@JoinColumn(name="codigo_tra")
	private SwoTratamiento swoTratamiento;

	public SwoProcedimiento() {
	}

	public Integer getCodigoPro() {
		return this.codigoPro;
	}

	public void setCodigoPro(Integer codigoPro) {
		this.codigoPro = codigoPro;
	}

	public Integer getCantidadProc() {
		return this.cantidadProc;
	}

	public void setCantidadProc(Integer cantidadProc) {
		this.cantidadProc = cantidadProc;
	}

	public String getDescripcionPro() {
		return this.descripcionPro;
	}

	public void setDescripcionPro(String descripcionPro) {
		this.descripcionPro = descripcionPro;
	}

	public List<SwoProceArticulo> getSwoProceArticulos() {
		return this.swoProceArticulos;
	}

	public void setSwoProceArticulos(List<SwoProceArticulo> swoProceArticulos) {
		this.swoProceArticulos = swoProceArticulos;
	}

	public SwoProceArticulo addSwoProceArticulo(SwoProceArticulo swoProceArticulo) {
		getSwoProceArticulos().add(swoProceArticulo);
		swoProceArticulo.setSwoProcedimiento(this);

		return swoProceArticulo;
	}

	public SwoProceArticulo removeSwoProceArticulo(SwoProceArticulo swoProceArticulo) {
		getSwoProceArticulos().remove(swoProceArticulo);
		swoProceArticulo.setSwoProcedimiento(null);

		return swoProceArticulo;
	}

	public SwoTratamiento getSwoTratamiento() {
		return this.swoTratamiento;
	}

	public void setSwoTratamiento(SwoTratamiento swoTratamiento) {
		this.swoTratamiento = swoTratamiento;
	}

}