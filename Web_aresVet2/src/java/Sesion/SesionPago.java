/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sesion;

import Entidades.Cita;
import Entidades.Pago;
import EntidadesLocales.TarjetaCredito;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author KrauseRPC
 */
@ManagedBean(name = "SesionPago")
@SessionScoped
public class SesionPago implements Serializable {
     
    @EJB
    private CitaFacadeLocal manejadorCita;
    @EJB
    private ClinicaveterinariaFacadeLocal manejadorClinica;
    @EJB
    private VeterinarioFacadeLocal manejadorVeterinario;
    @EJB
    private ClienteFacadeLocal manejadorCliente;
    @EJB
    private PagoFacadeLocal manejadorPago;
    private TarjetaCredito tarjetaCredito;
    private Cita citaCancelar;
    private float costoServicio= (float) 0.75;
    FacesContext facesContext;
    /**
     * Creates a new instance of SesionPago
     */
    public SesionPago() {
   
    }
    
    @PostConstruct
    public void inicio(){
        tarjetaCredito= new TarjetaCredito();
        obtenerCita();
    }
    public void obtenerCita(){
        facesContext = FacesContext.getCurrentInstance();
        Map<String, String> parametros = facesContext.getExternalContext().getRequestParameterMap();
        citaCancelar = (Cita) facesContext.getExternalContext().getSessionMap().get("citaVirtual");

    }
    
    public String realizarPago(){
        if(manejadorCita.encontrarCitaPorCliente(citaCancelar.getIdcliente(),"Espera", "Virtual")){
            manejadorCita.create(citaCancelar);
            Pago pago= new Pago();
            pago.setIdcita(citaCancelar);
            pago.setNumtarjeta(tarjetaCredito.getNumTarjeta());
            pago.setPago(citaCancelar.getPrecio()+ costoServicio);
            manejadorPago.create(pago);
        return "ChatVirtual";
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Ya haz solicitado una Cita"));
        }
        return null;
        
        
    }
    
   

    public TarjetaCredito getTarjetaCredito() {
        return tarjetaCredito;
    }

    public void setTarjetaCredito(TarjetaCredito tarjetaCredito) {
        this.tarjetaCredito = tarjetaCredito;
    }

    public Cita getCitaCancelar() {
        return citaCancelar;
    }

    public void setCitaCancelar(Cita citaCancelar) {
        this.citaCancelar = citaCancelar;
    }

    public float getCostoServicio() {
        return costoServicio;
    }

    public void setCostoServicio(float costoServicio) {
        this.costoServicio = costoServicio;
    }
    
    
    
    
    
    
    
    
}
