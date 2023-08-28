/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author KrauseRPC
 */
@Entity
@Table(name = "clinicaveterinaria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clinicaveterinaria.findAll", query = "SELECT c FROM Clinicaveterinaria c"),
    @NamedQuery(name = "Clinicaveterinaria.findByIdclinicaveterinaria", query = "SELECT c FROM Clinicaveterinaria c WHERE c.idclinicaveterinaria = :idclinicaveterinaria"),
    @NamedQuery(name = "Clinicaveterinaria.findByNombre", query = "SELECT c FROM Clinicaveterinaria c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Clinicaveterinaria.findByRuc", query = "SELECT c FROM Clinicaveterinaria c WHERE c.ruc = :ruc"),
    @NamedQuery(name = "Clinicaveterinaria.findByPassword", query = "SELECT c FROM Clinicaveterinaria c WHERE c.password = :password"),
    @NamedQuery(name = "Clinicaveterinaria.findByDireccion", query = "SELECT c FROM Clinicaveterinaria c WHERE c.direccion = :direccion"),
    @NamedQuery(name = "Clinicaveterinaria.findByHorarioatencion", query = "SELECT c FROM Clinicaveterinaria c WHERE c.horarioatencion = :horarioatencion"),
    @NamedQuery(name = "Clinicaveterinaria.findByTelefono", query = "SELECT c FROM Clinicaveterinaria c WHERE c.telefono = :telefono"),
    @NamedQuery(name = "Clinicaveterinaria.findByPrecioatencion", query = "SELECT c FROM Clinicaveterinaria c WHERE c.precioatencion = :precioatencion"),
    @NamedQuery(name = "Clinicaveterinaria.validarUsuario", query = "SELECT c FROM Clinicaveterinaria c WHERE c.password = :password AND c.email = :email")})
public class Clinicaveterinaria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idclinicaveterinaria")
    private Integer idclinicaveterinaria;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ruc")
    private int ruc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "horarioatencion")
    private String horarioatencion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precioatencion")
    private float precioatencion;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "email")
    private String email;
    @OneToMany(mappedBy = "idclinicaveterinaria")
    private Collection<Veterinario> veterinarioCollection;
    @OneToMany(mappedBy = "idclinicaveterinaria")
    private Collection<Cita> citaCollection;

    public Clinicaveterinaria() {
    }

    public Clinicaveterinaria(Integer idclinicaveterinaria) {
        this.idclinicaveterinaria = idclinicaveterinaria;
    }

    public Clinicaveterinaria(Integer idclinicaveterinaria, int ruc, String password, String direccion, String horarioatencion, String telefono, float precioatencion) {
        this.idclinicaveterinaria = idclinicaveterinaria;
        this.ruc = ruc;
        this.password = password;
        this.direccion = direccion;
        this.horarioatencion = horarioatencion;
        this.telefono = telefono;
        this.precioatencion = precioatencion;
    }

    public Integer getIdclinicaveterinaria() {
        return idclinicaveterinaria;
    }

    public void setIdclinicaveterinaria(Integer idclinicaveterinaria) {
        this.idclinicaveterinaria = idclinicaveterinaria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRuc() {
        return ruc;
    }

    public void setRuc(int ruc) {
        this.ruc = ruc;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getHorarioatencion() {
        return horarioatencion;
    }

    public void setHorarioatencion(String horarioatencion) {
        this.horarioatencion = horarioatencion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public float getPrecioatencion() {
        return precioatencion;
    }

    public void setPrecioatencion(float precioatencion) {
        this.precioatencion = precioatencion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public Collection<Veterinario> getVeterinarioCollection() {
        return veterinarioCollection;
    }

    public void setVeterinarioCollection(Collection<Veterinario> veterinarioCollection) {
        this.veterinarioCollection = veterinarioCollection;
    }

    @XmlTransient
    public Collection<Cita> getCitaCollection() {
        return citaCollection;
    }

    public void setCitaCollection(Collection<Cita> citaCollection) {
        this.citaCollection = citaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idclinicaveterinaria != null ? idclinicaveterinaria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clinicaveterinaria)) {
            return false;
        }
        Clinicaveterinaria other = (Clinicaveterinaria) object;
        if ((this.idclinicaveterinaria == null && other.idclinicaveterinaria != null) || (this.idclinicaveterinaria != null && !this.idclinicaveterinaria.equals(other.idclinicaveterinaria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Clinicaveterinaria[ idclinicaveterinaria=" + idclinicaveterinaria + " ]";
    }
    
}
