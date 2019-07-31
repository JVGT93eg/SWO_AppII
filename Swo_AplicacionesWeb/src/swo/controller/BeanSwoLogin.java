package swo.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;


import swo.model.dto.LoginDTO.LoginDTO;
import swo.model.manager.ManagerSeguridad;
import swo.model.manager.ManagerSwoAuditoria;
import swo.model.util.ModelUtil;




@Named
@javax.enterprise.context.SessionScoped
public class BeanSwoLogin implements Serializable  {
	private static final long serialVersionUID = 1L;
	private String codigoUsuario;
	private String clave;
	private int tipoUsuario;
	private boolean acceso;
	
	@EJB
	private ManagerSeguridad managerSeguridad;
	
	@EJB
	private ManagerSwoAuditoria managerAuditoria;
	private LoginDTO loginDTO;

	@PostConstruct
	public void inicializar() {
		loginDTO=new LoginDTO();
	}
	

	/**
	 * Action que permite el acceso al sistema.
	 * @return
	 */
	public String accederSistema(){
		acceso=false;
		try {
			loginDTO=managerSeguridad.accederSistema(codigoUsuario, clave);
			//verificamos el acceso del usuario:
			tipoUsuario=loginDTO.getTipoUsuario();
			//redireccion dependiendo del tipo de usuario:
			managerAuditoria.crearEvento(codigoUsuario, this.getClass(), "accederSistema", "Inicia Sesión");
			System.out.println(loginDTO.getRutaAcceso());
			return loginDTO.getRutaAcceso()+"?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.crearMensajeError(e.getMessage());
		}
		return "";
	}

	public String salirSistema() {
		System.out.println("salirSistema");
		try {
			managerAuditoria.crearEvento(loginDTO.getCodigoUsuario(), this.getClass(), "salisSistema", "Cerra Sesión");
		} catch (Exception e) {
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/Index.html?faces-redirect=true";
	}
	
	public void actionVerificar() {
		ExternalContext ec=FacesContext.getCurrentInstance().getExternalContext();
		String req=ec.getRequestPathInfo();
		try {
			if(loginDTO==null || ModelUtil.isEmpty(loginDTO.getRutaAcceso())) {
				ec.redirect(ec.getRequestContextPath()+"/Index.html");
			}else {
				if(req.contains("/Admin") && loginDTO.getRutaAcceso().startsWith("/Admin"))
					return;
				if(req.contains("/VistasDoctor") && loginDTO.getRutaAcceso().startsWith("/VistasDoctor"))
					return;
				if(req.contains("/VistasSecretaria") && loginDTO.getRutaAcceso().startsWith("/VistasSecretaria"))
					return;
				if(req.contains("/VistasInventario") && loginDTO.getRutaAcceso().startsWith("/VistasInventario"))
					return;
				System.out.println("sinLogin");
				try {
					managerAuditoria.crearEvento(loginDTO.getCodigoUsuario(), this.getClass(), "sinLogin", "Acceso Denegado");
				} catch (Exception e) {
					e.printStackTrace();
				}
				ec.redirect(ec.getRequestContextPath() + "/Index.html");
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public String getCodigoUsuario() {
		return codigoUsuario;
	}


	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}


	public String getClave() {
		return clave;
	}


	public void setClave(String clave) {
		this.clave = clave;
	}


	public int getTipoUsuario() {
		return tipoUsuario;
	}


	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}


	public boolean isAcceso() {
		return acceso;
	}


	public void setAcceso(boolean acceso) {
		this.acceso = acceso;
	}


	public LoginDTO getLoginDTO() {
		return loginDTO;
	}


	public void setLoginDTO(LoginDTO loginDTO) {
		this.loginDTO = loginDTO;
	}
	
	
}
