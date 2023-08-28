package Sesion;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import Entidades.Cita;
import Entidades.Cliente;
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
@ManagedBean(name = "SesionCliente")
@SessionScoped
public class SesionCliente implements Serializable {

    @EJB
    private ClienteFacadeLocal manejadorCliente;
    @EJB
    private CitaFacadeLocal manejadorCita;
    private Cliente clienteSesion;
    private String password;
    private List<Cita> citasAceptadas;

    /**
     * Creates a new instance of SesionCliente
     */
    public SesionCliente() {
    }

    public void verificarSesiones() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();

            Cliente us = (Cliente) context.getExternalContext().getSessionMap().get("cliente");

            setClienteSesion(us);

            notificaciones();

            if (us == null) {

                context.getExternalContext().redirect("LoginCliente.xhtml");
            }

        } catch (Exception e) {
        }

    }
    
    public String cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "LoginCliente.xhtml";
    }

    public void editarCliente() {
        String passwordAntigua = manejadorCliente.find(clienteSesion.getIdcliente()).getPassword();
        if (getPassword().equals(passwordAntigua)) {
            if (clienteSesion.getPassword().equals("")) {
                clienteSesion.setPassword(passwordAntigua);
            }
            manejadorCliente.edit(clienteSesion);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Exito", "Los cambios han sido registrados"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "La contraseña es incorrecta"));
        }
    }

    public void notificaciones() {
        List<Cita> estadoCitas = manejadorCita.encontrarCitaPorCliente2(clienteSesion);
        this.setCitasAceptadas(estadoCitas);

    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public Cliente getClienteSesion() {
        return clienteSesion;
    }

    public void setClienteSesion(Cliente clienteSesion) {
        this.clienteSesion = clienteSesion;
    }

    public List<Cita> getCitasAceptadas() {
        return citasAceptadas;
    }

    public void setCitasAceptadas(List<Cita> citasAceptadas) {
        this.citasAceptadas = citasAceptadas;
    }
}
