package Sesion;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Entidades.*;
import EntidadesLocales.Mensaje;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author KrauseRPC
 */
@ManagedBean(name = "CitaVirtual")
@SessionScoped
public class CitaVirtual {

    private Cita citaVirtualSesion;
    private Cliente clienteSesion;
    private Veterinario veterinarioSesion;
    FacesContext facesContext;
    private String mensajeText;
    private List<Mensaje> chatVirtual= new ArrayList<>();

    /**
     * Creates a new instance of CitaVirtual
     */
    public CitaVirtual() {
    }

    public void verificarCitaVirtual() {
        try {
            ingresarVeterinario();
            ingresarCliente();

        } catch (Exception e) {
        }

    }


    public String ingresarCliente() {
        facesContext = FacesContext.getCurrentInstance();
        Map<String, String> parametros = facesContext.getExternalContext().getRequestParameterMap();
        Cliente clienteSesion = (Cliente) facesContext.getExternalContext().getSessionMap().get("cliente");
        setClienteSesion(clienteSesion);
        return null;
    }

    public String ingresarVeterinario() {
        facesContext = FacesContext.getCurrentInstance();
        Map<String, String> parametros = facesContext.getExternalContext().getRequestParameterMap();
        Veterinario VeterinarioSesion = (Veterinario) facesContext.getExternalContext().getSessionMap().get("veterinario");
        setVeterinarioSesion(VeterinarioSesion);
        return null;
    }


    public void enviarMensajeVeterinario() {
        Mensaje mensaje = new Mensaje();
        mensaje.setRemitente(getClienteSesion());
        getChatVirtual().add(mensaje);
    }

    public void pruebaChat() {
        Mensaje mensaje = new Mensaje();
        mensaje.setRemitente(clienteSesion);
        mensaje.setMensaje(getMensajeText());
        chatVirtual.add(mensaje);
        mensajeText=null;
    }
    
    
    public void pruebaChatVeterinario() {

        Mensaje mensaje = new Mensaje();
        mensaje.setRemitente(veterinarioSesion);
        mensaje.setMensaje(getMensajeText());
        chatVirtual.add(mensaje);
        mensajeText=null;
    }

    public Cita getCitaVirtualSesion() {
        return citaVirtualSesion;
    }

    public void setCitaVirtualSesion(Cita citaVirtualSesion) {
        this.citaVirtualSesion = citaVirtualSesion;
    }

    public Cliente getClienteSesion() {
        return clienteSesion;
    }

    public void setClienteSesion(Cliente clienteSesion) {
        this.clienteSesion = clienteSesion;
    }

    public Veterinario getVeterinarioSesion() {
        return veterinarioSesion;
    }

    public void setVeterinarioSesion(Veterinario veterinarioSesion) {
        this.veterinarioSesion = veterinarioSesion;
    }

    public List<Mensaje> getChatVirtual() {
        return chatVirtual;
    }

    public void setChatVirtual(List<Mensaje> chatVirtual) {
        this.chatVirtual = chatVirtual;
    }

    public String getMensajeText() {
        return mensajeText;
    }

    public void setMensajeText(String mensajeText) {
        this.mensajeText = mensajeText;
    }
}
