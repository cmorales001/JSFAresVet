package Managed;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Entidades.Clinicaveterinaria;
import Sesion.ClinicaveterinariaFacadeLocal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author KrauseRPC
 */
@ManagedBean(name = "ManagedClinica")
@RequestScoped
public class ManagedClinica {

    /**
     * Creates a new instance of ManagedClinica
     */
    @EJB
    private ClinicaveterinariaFacadeLocal manejadorClinica;
    private Clinicaveterinaria clinicaVeterinaria;
    private List<Clinicaveterinaria> listaClinicas;
    private String email;
    private String password;
    FacesContext facesContext;

    public ManagedClinica() {
    }

    @PostConstruct
    private void inicio() {
        clinicaVeterinaria = new Clinicaveterinaria();
        listarClinica();

    }

    public void listarClinica() {
        setListaClinicas(manejadorClinica.findAll());

    }

    public void crearClinica() {
        String redireccion = null;
        try {
            if (!existeClinica(clinicaVeterinaria)) {
                manejadorClinica.create(clinicaVeterinaria);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Exito", "Clinica Nueva Registrada"));
                clinicaVeterinaria=null;
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Una clínica con ese correo electrónico ya existe"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error Base de Datos"));
        }

    }

    public String iniciarSesionClinica() {
        Clinicaveterinaria clinica;
        String redireccion = null;

        try {
            clinica = manejadorClinica.validarClinicaVeterinaria(this.getEmail(), this.getPassword());
            if (clinica != null) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("clinica", clinica);
                redireccion = "/InicioSesionClinica.xhtml";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Credenciales Incorrectas"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error Base de Datos"));
        }
        return redireccion;
    }

    public String registrarse() {
        return "Registro";
    }
    
    // metodo para verificar si existe una clinica
    public boolean existeClinica(Clinicaveterinaria clinicaVeterinaria) {
        boolean valor = false;
        // se obtiene la lista de clientes para comparar su atributo email con el que se recibio como parametro 
        for (Clinicaveterinaria clinica : this.listaClinicas) {
            if (clinica.getEmail().equals(clinicaVeterinaria.getEmail())) {
                valor = true;
                // el bucle for se detiene cuando encuentra la coincidencia del email
                break;
            }
        }
        return valor;
    }


    //gas
    public Clinicaveterinaria getClinicaVeterinaria() {
        return clinicaVeterinaria;
    }

    public void setClinicaVeterinaria(Clinicaveterinaria clinicaVeterinaria) {
        this.clinicaVeterinaria = clinicaVeterinaria;
    }


    public List<Clinicaveterinaria> getListaClinicas() {
        return listaClinicas;
    }

    public void setListaClinicas(List<Clinicaveterinaria> listaClinicas) {
        this.listaClinicas = listaClinicas;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
