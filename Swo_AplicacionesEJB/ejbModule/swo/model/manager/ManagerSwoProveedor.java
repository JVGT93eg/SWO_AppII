package swo.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import swo.model.entities.SwoProveedore;



/**
 * Session Bean implementation class ManagerSwoProveedor
 */
@Stateless
@LocalBean
public class ManagerSwoProveedor {
	@PersistenceContext(unitName="bddswoDS")
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public ManagerSwoProveedor() {
        // TODO Auto-generated constructor stub
    }
    public List<SwoProveedore> findAllProveedores(){
    	List<SwoProveedore> listaPrveedores=em.createQuery("SELECT p FROM SwoProveedore p").getResultList();
    	return listaPrveedores;
    }
    public SwoProveedore findOneProveedor(int codigoProv) throws Exception{
    	SwoProveedore proveedorEncontrado=em.find(SwoProveedore.class, codigoProv);
    	if(proveedorEncontrado==null) {
    		throw new Exception("Proveedor inexistente");
    	}
    	return proveedorEncontrado;
    }
    public void insertProveedor(SwoProveedore nuevoProveedor) {
    	em.persist(nuevoProveedor);
    }
    public void deleteProveedor(int codigoProv) {
    	SwoProveedore provedor = em.find(SwoProveedore.class, codigoProv);
    	em.remove(provedor);
    }
    public void updateProveedor(SwoProveedore pron) {
    	SwoProveedore proveedor = em.find(SwoProveedore.class, pron.getCodigoProv());
    	proveedor.setNombreProv(pron.getNombreProv());
    	proveedor.setDireccionProv(pron.getDireccionProv());
    	proveedor.setTelefonoProv(pron.getTelefonoProv());
    	proveedor.setEmailProv(pron.getEmailProv());
    	proveedor.setCuidadProv(pron.getCuidadProv());
    	proveedor.setDescripcionProv(pron.getDescripcionProv());
    	em.merge(proveedor);
    }
}
