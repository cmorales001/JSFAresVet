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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author KrauseRPC
 */
@Entity
@Table(name = "veterinario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Veterinario.findAll", query = "SELECT v FROM Veterinario v"),
    @NamedQuery(name = "Veterinario.findByIdveterinario", query = "SELECT v FROM Veterinario v WHERE v.idveterinario = :idveterinario"),
    @NamedQuery(name = "Veterinario.findByEstado", query = "SELECT v FROM Veterinario v WHERE v.estado = :estado"),
    @NamedQuery(name = "Veterinario.findByNombres", query = "SELECT v FROM Veterinario v WHERE v.nombres = :nombres"),
    @NamedQuery(name = "Veterinario.findByApellidos", query = "SELECT v FROM Veterinario v WHERE v.apellidos = :apellidos"),
    @NamedQuery(name = "Veterinario.findByEmail", query = "SELECT v FROM Veterinario v WHERE v.email = :email"),
    @NamedQuery(name = "Veterinario.encontrarPorClinicaEstado", query = "SELECT v FROM Veterinario v WHERE v.idclinicaveterinaria = :idclinicaveterinaria AND v.estado = :estado"),
    @NamedQuery(name = "Veterinario.verificarUsuario", query = "SELECT c FROM Veterinario c WHERE c.password = :password AND c.email = :email")})
public class Veterinario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idveterinario")
    private Integer idveterinario;
    @Size(max = 45)
    @Column(name = "estado")
    private String estado;
    @Size(max = 45)
    @Column(name = "nombres")
    private String nombres;
    @Size(max = 45)
    @Column(name = "apellidos")
    private String apellidos;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "email")
    private String email;
    @Size(max = 45)
    @Column(name = "password")
    private String password;
    @JoinColumn(name = "idclinicaveterinaria", referencedColumnName = "idclinicaveterinaria")
    @ManyToOne
    private Clinicaveterinaria idclinicaveterinaria;
    @OneToMany(mappedBy = "idveterinario")
    private Collection<Cita> citaCollection;

    public Veterinario() {
    }

    public Veterinario(Integer idveterinario) {
        this.idveterinario = idveterinario;
    }

    public Integer getIdveterinario() {
        return idveterinario;
    }

    public void setIdveterinario(Integer idveterinario) {
        this.idveterinario = idveterinario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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

    public Clinicaveterinaria getIdclinicaveterinaria() {
        return idclinicaveterinaria;
    }

    public void setIdclinicaveterinaria(Clinicaveterinaria idclinicaveterinaria) {
        this.idclinicaveterinaria = idclinicaveterinaria;
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
        hash += (idveterinario != null ? idveterinario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Veterinario)) {
            return false;
        }
        Veterinario other = (Veterinario) object;
        if ((this.idveterinario == null && other.idveterinario != null) || (this.idveterinario != null && !this.idveterinario.equals(other.idveterinario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombres+" "+ apellidos;
    }
    
}
