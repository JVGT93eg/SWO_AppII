package swo.model.manager;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import swo.model.dto.LoginDTO.LoginDTO;
import swo.model.entities.SwoUsuario;

@Stateless
@LocalBean
public class ManagerSeguridad {
	@EJB
	private ManagerDAO managerDAO;
    /**
     * Default constructor. 
     */
    public ManagerSeguridad() {
        
    }
    
    /**
	 * Metodo que le permite a un usuario acceder al sistema.
	 * @param codigoUsuario Identificador del usuario.
	 * @param clave Clave de acceso.
	 * @return Retorna el tipo de usuario. Puede tener dos valores:
	 * 			SP (supervisor) o VD (vendedor).
	 * @throws Exception Cuando no coincide la clave proporcionada o si ocurrio
	 * un error con la consulta a la base de datos.
	 */
	public LoginDTO accederSistema(String codigoUsuario,String clave) throws Exception{
		SwoUsuario usuario=(SwoUsuario)managerDAO.findById(SwoUsuario.class, codigoUsuario);
		
		if(usuario==null)
			throw new Exception("Error en usuario y/o clave."+usuario);
		
		if(!usuario.getClaveUsu().equals(clave))
			throw new Exception(usuario.getClaveUsu()+" Error en usuario y/o clave. "+clave);
		
		LoginDTO loginDTO=new LoginDTO();
		
		loginDTO.setUsuario(usuario.getNombreUsu());
		loginDTO.setTipoUsuario(usuario.getSwoRole().getCodigoRol());
		System.out.println(usuario.getSwoRole().getNombreRol());
		loginDTO.setCodigoUsuario(usuario.getCedulaUsu());
		
		//dependiendo del tipo de usuario, configuramos la ruta de acceso a las pags web:
		if(usuario.getSwoRole().getNombreRol().equals("Administrador"))
			loginDTO.setRutaAcceso("/Admin/index.xhtml");
		else if(usuario.getSwoRole().getNombreRol().equals("Doctor"))
			loginDTO.setRutaAcceso("/VistasDoctor/index.xhtml");
		else if(usuario.getSwoRole().getNombreRol().equals("Secretario"))
			loginDTO.setRutaAcceso("/VistasSecretaria/index.xhtml");
		else if(usuario.getSwoRole().getNombreRol().equals("Inventariado"))
			loginDTO.setRutaAcceso("/VistasInventario/index.xhtml");
		return loginDTO;
	}
	
	public SwoUsuario findUsuarioById(String codigoUsuario) throws Exception {
		SwoUsuario usuario=(SwoUsuario)managerDAO.findById(SwoUsuario.class, codigoUsuario);
		return usuario;
	}

}
