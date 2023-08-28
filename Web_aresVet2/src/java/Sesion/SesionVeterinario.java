package Sesion;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import Entidades.*;
import Sesion.CitaFacadeLocal;
import Sesion.ClinicaveterinariaFacadeLocal;
import Sesion.VeterinarioFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author KrauseRPC
 */
@ManagedBean(name = "SesionVeterinario")
@SessionScoped
public class SesionVeterinario implements Serializable {

    @EJB
    private CitaFacadeLocal manejadorCita;
    @EJB
    private VeterinarioFacadeLocal manejadorVeterinario;
    private List<Cita> citasVirtuales;
    private Veterinario veterinarioSesion;
    private int idCita;
    private String password;

    /**
     * Creates a new instance of SesionVeterinario
     */
    public SesionVeterinario() {
    }

    public void verificarSesionVeterinario() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Veterinario us = (Veterinario) context.getExternalContext().getSessionMap().get("veterinario");
            setVeterinarioSesion(us);
            listarCitas();
            if (us == null) {
                context.getExternalContext().redirect("LoginVeterinario.xhtml");
            }

        } catch (Exception e) {
        }

    }

    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "LoginVeterinario.xhtml";
    }

    public void editarVeterinario() {
        String passwordAntigua = manejadorVeterinario.find(veterinarioSesion.getIdveterinario()).getPassword();
        if (veterinarioSesion.getPassword().equals("")) {
            veterinarioSesion.setPassword(passwordAntigua);
        }
        if (getPassword().equals(passwordAntigua)) {
            manejadorVeterinario.edit(veterinarioSesion);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Exito", "Los cambios han sido registrados"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "La contraseña es incorrecta"));
        }
    }

    public void listarCitas() {
        setCitasVirtuales(manejadorCita.encontrarCitaPorClinicaEstado(veterinarioSesion.getIdclinicaveterinaria(), "Espera", "Virtual"));
    }

    public String aceptarCita() {
        Cita citaAceptada;
        citaAceptada = manejadorCita.find(getIdCita());
        citaAceptada.setEstado("Atendida");
        citaAceptada.setIdveterinario(getVeterinarioSesion());
        manejadorCita.edit(citaAceptada);
        return "ChatVirtualVeterinario.xhtml";
    }

    public Veterinario getVeterinarioSesion() {
        return veterinarioSesion;
    }

    public void setVeterinarioSesion(Veterinario veterinarioSesion) {
        this.veterinarioSesion = veterinarioSesion;
    }

    public List<Cita> getCitasVirtuales() {
        return citasVirtuales;
    }

    public void setCitasVirtuales(List<Cita> citasVirtuales) {
        this.citasVirtuales = citasVirtuales;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
