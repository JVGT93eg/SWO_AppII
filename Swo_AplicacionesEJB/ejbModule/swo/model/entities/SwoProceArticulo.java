package swo.model.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the swo_proce_articulos database table.
 * 
 */
@Entity
@Table(name="swo_proce_articulos")
@NamedQuery(name="SwoProceArticulo.findAll", query="SELECT s FROM SwoProceArticulo s")
public class SwoProceArticulo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_pro_art", unique=true, nullable=false)
	private Integer codigoProArt;

	//bi-directional many-to-one association to SwoArticulo
	@ManyToOne
	@JoinColumn(name="codigo_art")
	private SwoArticulo swoArticulo;

	//bi-directional many-to-one association to SwoProcedimiento
	@ManyToOne
	@JoinColumn(name="codigo_pro")
	private SwoProcedimiento swoProcedimiento;

	public SwoProceArticulo() {
	}

	public Integer getCodigoProArt() {
		return this.codigoProArt;
	}

	public void setCodigoProArt(Integer codigoProArt) {
		this.codigoProArt = codigoProArt;
	}

	public SwoArticulo getSwoArticulo() {
		return this.swoArticulo;
	}

	public void setSwoArticulo(SwoArticulo swoArticulo) {
		this.swoArticulo = swoArticulo;
	}

	public SwoProcedimiento getSwoProcedimiento() {
		return this.swoProcedimiento;
	}

	public void setSwoProcedimiento(SwoProcedimiento swoProcedimiento) {
		this.swoProcedimiento = swoProcedimiento;
	}

}