 package Managed;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import Entidades.Clinicaveterinaria;
import Entidades.Veterinario;
import Sesion.ClinicaveterinariaFacadeLocal;
import Sesion.VeterinarioFacadeLocal;
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
@ManagedBean(name = "ManagedVeterinario")
@RequestScoped
public class ManagedVeterinario {

    @EJB
    private ClinicaveterinariaFacadeLocal manejadorClinica;
    @EJB
    private VeterinarioFacadeLocal manejadorVeterinario;
    private Veterinario veterinario;
    private Clinicaveterinaria clinicaveterinaria;
    private List<Veterinario> listaVeterinarios;
    private List<Clinicaveterinaria> listaClinicas;
    private String email;
    private String password;
    private int idClinica;

    public ManagedVeterinario() {
    }

    @PostConstruct
    private void inicio() {
        veterinario = new Veterinario();
        listarVeterinario();
        listarClinica();
    }

    public void crearVeterinario() {

        idClinicaVeterinaria();
        try {
            if (!existeVeterinario(veterinario)) {
                veterinario.setEstado("Rechazado");
                manejadorVeterinario.create(veterinario);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Exito", "Nuevo veterinario Registrado"));
                veterinario=null;
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "No se ha creado nuevo veterinario"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error Base de Datos"));
        }


    }

    public String iniciarSesionVeterinario() {
        Veterinario veterinario;
        String redireccion = null;

        try {
            veterinario = manejadorVeterinario.validarVeterinario(this.getEmail(), this.getPassword());
            if (veterinario != null) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("veterinario", veterinario);
                redireccion = "/InicioSesionVeterinario.xhtml";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Credenciales Incorrectas"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error Base de Datos"));
        }
        return redireccion;
    }

    public boolean existeVeterinario(Veterinario cliente) {
        boolean valor = false;
        // se obtiene la lista de clientes para comparar su atributo email con el que se recibio como parametro 
        for (Veterinario persona : this.listaVeterinarios) {
            if (persona.getEmail().equals(cliente.getEmail())) {
                valor = true;
                // el bucle for se detiene cuando encuentra la coincidencia del email
                break;
            }
        }
        return valor;
    }

    public void idClinicaVeterinaria() {
        this.clinicaveterinaria = manejadorClinica.buscarClinicaVeterinaria(this.getIdClinica());
        this.veterinario.setIdclinicaveterinaria(clinicaveterinaria);
    }

    public void listarVeterinario() {
        setListaVeterinarios(manejadorVeterinario.findAll());
    }

    public void listarClinica() {
        setListaClinicas(manejadorClinica.findAll());
    }

    //gas
    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    public List<Veterinario> getListaVeterinarios() {
        return listaVeterinarios;
    }

    public void setListaVeterinarios(List<Veterinario> listaVeterinarios) {
        this.listaVeterinarios = listaVeterinarios;
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

    public int getIdClinica() {
        return idClinica;
    }

    public void setIdClinica(int idClinica) {
        this.idClinica = idClinica;
    }

    public List<Clinicaveterinaria> getListaClinicas() {
        return listaClinicas;
    }

    public void setListaClinicas(List<Clinicaveterinaria> listaClinicas) {
        this.listaClinicas = listaClinicas;
    }

    public Clinicaveterinaria getClinicaveterinaria() {
        return clinicaveterinaria;
    }

    public void setClinicaveterinaria(Clinicaveterinaria clinicaveterinaria) {
        this.clinicaveterinaria = clinicaveterinaria;
    }
}
