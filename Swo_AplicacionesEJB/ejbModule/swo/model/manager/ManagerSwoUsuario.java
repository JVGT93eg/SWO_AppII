package swo.model.manager;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import swo.model.entities.SwoRole;
import swo.model.entities.SwoUsuario;

/**
 * Session Bean implementation class ManagerSwoUsuario
 */
@Stateless
@LocalBean
public class ManagerSwoUsuario {
	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public ManagerSwoUsuario() {
        // TODO Auto-generated constructor stub
    }
    
    public List<SwoUsuario> findAllSwoUsuarios(){
    	String consulta="SELECT s FROM SwoUsuario s  order by s.apellidoUsu";
    	Query q=em.createQuery(consulta, SwoUsuario.class);
    	return q.getResultList();
    }
    
    public SwoUsuario findSwoUsuarioByCedulaUsu(String cedulaUsu) {
    	return em.find(SwoUsuario.class, cedulaUsu);
    }

	public boolean validarCedula(String cedula) {
		boolean cedulaCorrecta = false;

		try {

			if (cedula.length() == 10) 
			{
				int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
				if (tercerDigito < 6) {
					int[] coefValCedula = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
					int verificador = Integer.parseInt(cedula.substring(9, 10));
					int suma = 0;
					int digito = 0;
					for (int i = 0; i < (cedula.length() - 1); i++) {
						digito = Integer.parseInt(cedula.substring(i, i + 1)) * coefValCedula[i];
						suma += ((digito % 10) + (digito / 10));
					}

					if ((suma % 10 == 0) && (suma % 10 == verificador)) {
						cedulaCorrecta = true;
					} else if ((10 - (suma % 10)) == verificador) {
						cedulaCorrecta = true;
					} else {
						cedulaCorrecta = false;
					}
				} else {
					cedulaCorrecta = false;
				}
			} else {
				cedulaCorrecta = false;
			}
		} catch (NumberFormatException nfe) {
			cedulaCorrecta = false;
		} catch (Exception err) {
			System.out.println("Una excepción ocurrió en el proceso de validación");
			cedulaCorrecta = false;
		}

		if (!cedulaCorrecta) {
			System.out.println("La Cédula ingresada es Incorrecta");
		}
		return cedulaCorrecta;
	}
	public boolean verificarLetras(String palabra) {
		boolean res=true;
		for(int i=0;i<palabra.length();i++) {
    		char c=palabra.charAt(i);
    		if(!Character.isAlphabetic(c)) {
    			res=false;
    			break;
    		}
    	}
		return res;
	}
	public boolean verificarNumeros(String palabra) {
		boolean res=true;
		for(int i=0;i<palabra.length();i++) {
    		char c=palabra.charAt(i);
    		if(!Character.isDigit(c)) {
    			res=false;
    			break;
    		}
    	}
		return res;
	}
	public boolean verificarContrasenia(String pass) {
		boolean res = false, l=false,n=false,e=false;
		for(int i=0;i<pass.length();i++) {
			char c=pass.charAt(i);
			if(Character.isUpperCase(c))
				l=true;
		}
		for(int i=0;i<pass.length();i++) {
			char c=pass.charAt(i);
			if(Character.isDigit(c))
				n=true;
		}
		for(int i=0;i<pass.length();i++) {
			char c=pass.charAt(i);
			if(Character.isDefined(c))
				e=true;
		}
		if(pass.length()>6 && l && n && e) {
			res=true;
		}
		return res;
	}
    public void validarSwoUsuario(SwoUsuario usuario) throws Exception{
    	boolean datosValidos=false;
    	datosValidos=validarCedula(usuario.getCedulaUsu());
    	if (!datosValidos)
    		throw new Exception("Cédula Incorrecta");
    	if (!verificarLetras(usuario.getNombreUsu()))
    		throw new Exception("Nombre con formato Incorrecto");
    	if (!verificarLetras(usuario.getApellidoUsu()))
    		throw new Exception("Apellidos con formato Incorrecto");
    	if((Integer.valueOf(usuario.getEdadUsu().intValue()))<2 || (Integer.valueOf(usuario.getEdadUsu().intValue()))>105)
    		throw new Exception("Error en edad");
    	if(!verificarNumeros(usuario.getTelefonoUsu()))
    		throw new Exception("Número telefónico incorrecto");
    	if((new Date()).before(usuario.getFechaNaciUsu()))
    		throw new Exception("Fecha de nacimiento Incorrecta");
    	if(!verificarContrasenia(usuario.getClaveUsu()))
    		throw new Exception("Clave de 8 caracteres de mínimo, 1 caracter especial, 1 número, 1 letra mayúscula y una minúscula");
    	
    }
    public void insertarSwoUsuario(SwoUsuario swoUsuario, Integer codigoRol) throws Exception{
    	if(findSwoUsuarioByCedulaUsu(swoUsuario.getCedulaUsu())!=null)
    		throw new Exception("Cédula ya registrada");
    	validarSwoUsuario(swoUsuario);
    	SwoRole swoRoles=em.find(SwoRole.class, codigoRol);
    	swoUsuario.setSwoRole(swoRoles);
    	em.persist(swoUsuario);   			
    }
        
    public void eliminarSwoUsuario(String cedulaUsu) {
    	SwoUsuario swoUsuario=findSwoUsuarioByCedulaUsu(cedulaUsu);
    	if(swoUsuario!=null)
    		em.remove(swoUsuario);
    }
    
    public void actualizarSwoUsuario( SwoUsuario swoUsuario) throws Exception{
    	SwoUsuario s= findSwoUsuarioByCedulaUsu(swoUsuario.getCedulaUsu());
    	if(s==null)
    		throw new Exception("No existe el usuario con el código especificado");
    	s.setCedulaUsu(swoUsuario.getCedulaUsu());
    	s.setNombreUsu(swoUsuario.getNombreUsu());
    	s.setApellidoUsu(swoUsuario.getApellidoUsu());
    	s.setClaveUsu(swoUsuario.getClaveUsu());
    	s.setEdadUsu(swoUsuario.getEdadUsu());
    	s.setTelefonoUsu(swoUsuario.getTelefonoUsu());
    	s.setEmailUsu(swoUsuario.getEmailUsu());
    	s.setDireccionUsu(swoUsuario.getDireccionUsu());
    	s.setFechaNaciUsu(swoUsuario.getFechaNaciUsu());
    	em.merge(s);
    }

}
