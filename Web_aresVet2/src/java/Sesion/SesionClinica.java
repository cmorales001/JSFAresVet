package Sesion;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Entidades.*;
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
@ManagedBean(name = "SesionClinica")
@SessionScoped
public class SesionClinica implements Serializable{

    @EJB
    private VeterinarioFacadeLocal manejadorVeterinario;
    @EJB
    private CitaFacadeLocal manejadorCita;
    @EJB
    private ClinicaveterinariaFacadeLocal manejadorClinica;
    private Clinicaveterinaria clinicaSesion;
    private List<Veterinario> veterinariosPorAceptar;
    private List<Veterinario> veterinariosAceptados;
    private List<Cita> citasHistorial;
    private List<Cita> citasEmergencia;
    private List<Cita> citasPresenciales;
    private Veterinario veterinarioAceptado;
    private int idVeterinario;
    private int idCita;
    private String password;

    /**
     * Creates a new instance of SesionCliente
     */
    public SesionClinica() {
    }

    public void verificarSesionClinica() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();

            Clinicaveterinaria clinicaSesionA = (Clinicaveterinaria) context.getExternalContext().getSessionMap().get("clinica");
            setClinicaSesion(clinicaSesionA);
            listarVeterinarios();
            listarCitas();
            if (clinicaSesionA == null) {

                context.getExternalContext().redirect("LoginClinica.xhtml");
            }

        } catch (Exception e) {
        }

    }
    
    public String cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "LoginClinica.xhtml";
    }
    
    public void editarClinica() {
        String passwordAntigua = manejadorClinica.find(clinicaSesion.getIdclinicaveterinaria()).getPassword();
        if (clinicaSesion.getPassword().equals("")) {
            clinicaSesion.setPassword(passwordAntigua);
        }
        if (getPassword().equals(passwordAntigua)) {
            manejadorClinica.edit(clinicaSesion);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Exito", "Los cambios han sido registrados"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "La contraseña es incorrecta"));
        }
    }

    //metodo para obtener los veterinarios aceptados y rechazados de la clinica en Sesion
    public void listarVeterinarios() {
        setVeterinariosAceptados(manejadorVeterinario.encontrarVeterinarioPorClinica(getClinicaSesion(), "Aceptado"));
        setVeterinariosPorAceptar(manejadorVeterinario.encontrarVeterinarioPorClinica(getClinicaSesion(), "Rechazado"));
    }

    public void AceptarVeterinario() {
        setVeterinarioAceptado(manejadorVeterinario.find(getIdVeterinario()));
        veterinarioAceptado.setEstado("Aceptado");
        manejadorVeterinario.edit(veterinarioAceptado);
    }
    
    public void aceptarCita() {
        Cita citaAceptada;
        citaAceptada=manejadorCita.find(getIdCita());
        citaAceptada.setEstado("Atendida");
        manejadorCita.edit(citaAceptada);
    }
    
    public void eliminarCita() {
        Cita citaAceptada;
        citaAceptada=manejadorCita.find(getIdCita());
        citaAceptada.setEstado("Eliminada");
        manejadorCita.edit(citaAceptada);
    }

    public void listarCitas() {
        setCitasHistorial(manejadorCita.encontrarCitaPorClinica(getClinicaSesion()));
        setCitasEmergencia(manejadorCita.encontrarCitaPorClinicaEstado(getClinicaSesion(), "Espera", "Emergencia"));
        setCitasPresenciales(manejadorCita.encontrarCitaPorClinicaEstado(getClinicaSesion(), "Espera", "Presencial"));
    }


    public void ListarCitasHistorial() {
        setCitasHistorial(manejadorCita.encontrarCitaPorClinica(getClinicaSesion()));
    }

    public Clinicaveterinaria getClinicaSesion() {
        return clinicaSesion;
    }

    public void setClinicaSesion(Clinicaveterinaria clinicaSesion) {
        this.clinicaSesion = clinicaSesion;
    }

    public List<Veterinario> getVeterinariosPorAceptar() {
        return veterinariosPorAceptar;
    }

    public void setVeterinariosPorAceptar(List<Veterinario> veterinariosPorAceptar) {
        this.veterinariosPorAceptar = veterinariosPorAceptar;
    }

    public List<Veterinario> getVeterinariosAceptados() {
        return veterinariosAceptados;
    }

    public void setVeterinariosAceptados(List<Veterinario> veterinariosAceptados) {
        this.veterinariosAceptados = veterinariosAceptados;
    }

    public Veterinario getVeterinarioAceptado() {
        return veterinarioAceptado;
    }

    public void setVeterinarioAceptado(Veterinario veterinarioAceptado) {
        this.veterinarioAceptado = veterinarioAceptado;
    }

    public int getIdVeterinario() {
        return idVeterinario;
    }

    public void setIdVeterinario(int idVeterinario) {
        this.idVeterinario = idVeterinario;
    }

    public List<Cita> getCitasHistorial() {
        return citasHistorial;
    }

    public void setCitasHistorial(List<Cita> citasHistorial) {
        this.citasHistorial = citasHistorial;
    }

    public List<Cita> getCitasEmergencia() {
        return citasEmergencia;
    }

    public void setCitasEmergencia(List<Cita> citasEmergencia) {
        this.citasEmergencia = citasEmergencia;
    }

    public List<Cita> getCitasPresenciales() {
        return citasPresenciales;
    }

    public void setCitasPresenciales(List<Cita> citasPresenciales) {
        this.citasPresenciales = citasPresenciales;
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
