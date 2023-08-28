package Managed;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import Entidades.Cliente;
import Sesion.ClienteFacadeLocal;
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
@ManagedBean(name = "ManagedCliente")
@RequestScoped
public class ManagedCliente {

    @EJB
    private ClienteFacadeLocal manejadorCliente;
    private Cliente cliente;
    private List<Cliente> listaCli;
    private String email;
    private String password;

    public ManagedCliente() {
    }

    @PostConstruct
    private void inicio() {
        cliente = new Cliente();
        listarCliente();
    }

    public void grabarCliente() {
        try {
            if (manejadorCliente.existeCliente(cliente.getEmail())== null) {
                manejadorCliente.create(cliente);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Exito", "Cliente Nuevo Registrado"));
                cliente=null;
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Un cliente con ese correo eléctronico ya existe"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error Base de Datos"));
        }
    }

    public void listarCliente() {
        setListaCli(manejadorCliente.findAll());
    }

    
    // metodo para iniciar sesion
    public String iniciarSesionCliente() {
        Cliente cli;
        String redireccion = null;

        try {
            cli = manejadorCliente.validarCliente(this.getEmail(), this.getPassword());
            if (cli != null) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cliente", cli);
                redireccion = "/InicioSesionCliente.xhtml";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Credenciales Incorrectas"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error Base de Datos"));
        }
        return redireccion;
    }

//    public String registrarse() {
//        return "Registro";
//    }

    //getters and setters
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getListaCli() {
        return listaCli;
    }

    public void setListaCli(List<Cliente> listaCli) {
        this.listaCli = listaCli;
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
