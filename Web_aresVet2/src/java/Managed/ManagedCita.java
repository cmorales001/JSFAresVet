package Managed;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Entidades.*;
import Sesion.CitaFacadeLocal;
import Sesion.ClienteFacadeLocal;
import Sesion.ClinicaveterinariaFacadeLocal;
import java.util.List;
import java.util.Map;
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
@ManagedBean(name = "ManagedCita")
@RequestScoped
public class ManagedCita {

    /**
     * Creates a new instance of ManagedCita
     */
    @EJB
    private CitaFacadeLocal manejadorCita;
    @EJB
    private ClinicaveterinariaFacadeLocal manejadorClinica;
    @EJB
    private ClienteFacadeLocal manejadorCliente;
    private Cita cita;
    private List<Cita> listaCitas;
    private Clinicaveterinaria clinicaVeterinaria;
    private Cliente cliente;
    private String fechaReservacion;
    private String horaReservacion;
    FacesContext facesContext;

    public ManagedCita() {
    }

    @PostConstruct
    private void inicio() {
        cita = new Cita();
        cita.setEstado("Espera");
        
    }

    public void grabarCita() {
        manejadorCita.create(cita);
    }

    public void listarCitas() {
        setListaCitas(manejadorCita.findAll());
    }

    public void registrarCita() {
        facesContext = FacesContext.getCurrentInstance();
        Map<String, String> parametros = facesContext.getExternalContext().getRequestParameterMap();
        
        int idClinica = Integer.parseInt((String) parametros.get("idClinica"));
        
        Cliente clienteSesion = (Cliente) facesContext.getExternalContext().getSessionMap().get("cliente");
        
        setClinicaVeterinaria(manejadorClinica.buscarClinicaVeterinaria(idClinica));
        setCliente(clienteSesion);
        
        cita.setIdcliente(cliente);
        cita.setIdclinicaveterinaria(clinicaVeterinaria);
    }

    public String citaVirtual() {
        
        registrarCita();
        cita.setTipocita("Virtual");
        cita.setPrecio(getClinicaVeterinaria().getPrecioatencion());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("citaVirtual", cita);
        return "RegistrarPago";
        
    }

    public void citaPresencial() {
        
        registrarCita();
        if(manejadorCita.encontrarCitaPorCliente(cliente,"Espera", "Presencial")){
        cita.setTipocita("Presencial");
        cita.setFechareservacion(getFechaReservacion()+" at "+getHoraReservacion());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Exito", "Tu cita ha sido registrada"));
        grabarCita();
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Ya haz solicitado una Cita"));
        }
    }

    public void citaEmergencia() {
        registrarCita();
        if(manejadorCita.encontrarCitaPorCliente(cliente,"Espera", "Emergencia")){
        cita.setTipocita("Emergencia");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Exito", "Tu cita ha sido registrada"));
        grabarCita();
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Ya haz solicitado una Cita"));
        }
    }

    public String elegirClinica() {
        facesContext = FacesContext.getCurrentInstance();
        
        Map<String, String> parametros = facesContext.getExternalContext().getRequestParameterMap();
        
        int idClinica = Integer.parseInt((String) parametros.get("idClinica"));
        
        setClinicaVeterinaria(manejadorClinica.buscarClinicaVeterinaria(idClinica));
        return "RegistrarCita.xhtml";
    }

    public String salirClinica() {
        clinicaVeterinaria = null;
        return "InicioSesionCliente.xhtml";
    }
    
    
// metodos Getter and Setters
    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }


    public List<Cita> getListaCitas() {
        return listaCitas;
    }

    public void setListaCitas(List<Cita> listaCitas) {
        this.listaCitas = listaCitas;
    }

    public Clinicaveterinaria getClinicaVeterinaria() {
        return clinicaVeterinaria;
    }

    public void setClinicaVeterinaria(Clinicaveterinaria clinicaVeterinaria) {
        this.clinicaVeterinaria = clinicaVeterinaria;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getHoraReservacion() {
        return horaReservacion;
    }

    public void setHoraReservacion(String horaReservacion) {
        this.horaReservacion = horaReservacion;
    }

    public String getFechaReservacion() {
        return fechaReservacion;
    }

    public void setFechaReservacion(String fechaReservacion) {
        this.fechaReservacion = fechaReservacion;
    }
}
