package swo.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import swo.model.entities.SwoArticulo;
import swo.model.entities.SwoDetallesInventario;
import swo.model.entities.SwoProveedore;
import swo.model.entities.SwoTipoTransaccion;


/**
 * Session Bean implementation class ManagerSwoDetallesInventario
 */
@Stateless
@LocalBean
public class ManagerSwoDetallesInventario {
	@PersistenceContext(unitName="bddswoDS")
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public ManagerSwoDetallesInventario() {
        // TODO Auto-generated constructor stub
    }
    public List<SwoDetallesInventario> findAllDetallesInventario(){
    	List<SwoDetallesInventario> listaDetalles=em.createQuery("SELECT d FROM SwoDetallesInventario d").getResultList();
    	return listaDetalles;
    }
    public SwoDetallesInventario findOneDetallesInventario(int codigoDetInv) {
    	SwoDetallesInventario detalle=em.find(SwoDetallesInventario.class, codigoDetInv);
    	return detalle;
    }
    public void insertDetalleInventario(SwoDetallesInventario detalle, int codigoArt, int codigoProv, int codigoTipoTrans) {
    	SwoArticulo articulo=em.find(SwoArticulo.class, codigoArt);
    	SwoProveedore proveedor=em.find(SwoProveedore.class, codigoProv);
    	SwoTipoTransaccion tipo=em.find(SwoTipoTransaccion.class, codigoTipoTrans);
    	detalle.setSwoArticulo(articulo);
    	detalle.setSwoProveedore(proveedor);
    	detalle.setSwoTipoTransaccion(tipo);
    	em.persist(detalle);
    }
    public void deleteDetalleInventario(int codigoDetInv) {
    	SwoDetallesInventario detalle=em.find(SwoDetallesInventario.class, codigoDetInv);
    	em.remove(detalle);
    }
    public void updateDetalleInventario(SwoDetallesInventario detalleAc, int codigoArt, int codigoProv, int codigoTipoTrans) {
    	SwoArticulo articulo=em.find(SwoArticulo.class, codigoArt);
    	SwoProveedore proveedor=em.find(SwoProveedore.class, codigoProv);
    	SwoTipoTransaccion tipo=em.find(SwoTipoTransaccion.class, codigoTipoTrans);
    	SwoDetallesInventario detalle=em.find(SwoDetallesInventario.class, detalleAc.getCodigoDetInv());
    	detalle.setCantidadTrans(detalleAc.getCantidadTrans());
    	detalle.setDescripcionDetInv(detalleAc.getDescripcionDetInv());
    	detalle.setFechaTrans(detalleAc.getFechaTrans());
    	detalle.setPrecioTrans(detalleAc.getPrecioTrans());
    	detalle.setSwoArticulo(articulo);
    	detalle.setSwoProveedore(proveedor);
    	detalle.setSwoTipoTransaccion(tipo);
    	em.merge(detalle);
    }
}
