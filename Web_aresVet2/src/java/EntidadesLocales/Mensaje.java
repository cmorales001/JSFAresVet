/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntidadesLocales;

/**
 *
 * @author KrauseRPC
 */
public class Mensaje {
    
    private Object remitente;
    private String mensaje;

    public Mensaje() {
    }

    public Object getRemitente() {
        return remitente;
    }

    public void setRemitente(Object remitente) {
        this.remitente = remitente;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return  "remitente:" + remitente + ", mensaje=" + mensaje ;
    }
    
    
    
    
}
