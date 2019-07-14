package swo.model.manager;


import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import swo.model.entities.SwoArticulo;
import swo.model.entities.SwoCategoria;
import swo.model.entities.SwoGenero;
import swo.model.entities.SwoPaciente;

/**
 * Session Bean implementation class MangerPaciente
 */
@Stateless
@LocalBean
public class MangerPaciente {
	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public MangerPaciente() {
		// TODO Auto-generated constructor stub
	}

	// Método que retorna la lista de la tabla Pacientes
	public List<SwoPaciente> findAllPacientes() {
		String consulta = ("SELECT s FROM SwoPaciente s");
		Query q = em.createQuery(consulta, SwoPaciente.class);
		return q.getResultList();

	}
	 public SwoGenero buscarPorCodigoG(int codGenero) {
		   SwoGenero genero = em.find(SwoGenero.class, codGenero);
		   return genero;
 }
	// metodo para insertar un paciente
	public void insertarPaciente(String cedula_pa, String nombre_pa,String apellido_pa,Date fecha_naci_pa, int codigo_ge_pa, String direccion_pa, String telefono_pa , String email_pa  
			) {
		SwoPaciente paciente = new SwoPaciente();
		SwoGenero genero = buscarPorCodigoG(codigo_ge_pa);
		paciente.setEmailPac(email_pa);
		paciente.setTelefonoPac(telefono_pa);
		paciente.setDireccionPac(direccion_pa);
		paciente.setFechaNaciPac(fecha_naci_pa);
		paciente.setApellidoPac(apellido_pa);
		paciente.setNombrePac(nombre_pa);
		paciente.setCedulaPac(cedula_pa);
		paciente.setSwoGenero(genero);

		em.persist(paciente);
	}

	// método de busqueda para el Pacientes
	public SwoPaciente findSwoPacienteBycodigopaci(Integer codigo_pacie) {
		return em.find(SwoPaciente.class, codigo_pacie);

	}

	// métod para eliminar
	public void eliminarPaciente(Integer codigo_pacie) {
		SwoPaciente paciente = findSwoPacienteBycodigopaci(codigo_pacie);
		if (paciente != null)
			em.remove(paciente);
	}

	// para actualizar
	public void actualizarSwoPaciente(SwoPaciente paciente) throws Exception {
		SwoPaciente e = findSwoPacienteBycodigopaci(paciente.getCodigoPac());
		SwoGenero genero = buscarPorCodigoG(paciente.getSwoGenero().getCodigoGen());
		if (e == null)
			throw new Exception("No existe el Paciente con la código especificado");
		e.setCedulaPac(paciente.getCedulaPac());
		e.setNombrePac(paciente.getNombrePac());
		e.setApellidoPac(paciente.getApellidoPac());
		e.setFechaNaciPac(paciente.getFechaNaciPac());
		e.setDireccionPac(paciente.getDireccionPac());
		e.setTelefonoPac(paciente.getTelefonoPac());
		e.setEmailPac(paciente.getEmailPac());
	   e.setSwoGenero(genero);
		em.merge(e);
	}

	// método que funciona para la lista de género
	public List<SwoGenero> listarGenero() {

		String sentencia = "SELECT s FROM SwoGenero s";
		Query q = em.createQuery(sentencia, SwoGenero.class);
		return q.getResultList();
	}

}
