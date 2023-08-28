/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "cita")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cita.findAll", query = "SELECT c FROM Cita c"),
    @NamedQuery(name = "Cita.encontrarporCliente", query = "SELECT c FROM Cita c WHERE c.idcliente = :idcliente"),
    @NamedQuery(name = "Cita.findByClienteEstado", query = "SELECT c FROM Cita c WHERE c.idcliente = :idcliente AND c.estado = :estado AND c.tipocita = :tipocita"),
    @NamedQuery(name = "Cita.encontrarPorClinica", query = "SELECT c FROM Cita c WHERE c.idclinicaveterinaria = :idclinicaveterinaria AND c.estado = :estado"),
    @NamedQuery(name = "Cita.encontrarPorClinicaEstado", query = "SELECT c FROM Cita c WHERE c.idclinicaveterinaria = :idclinicaveterinaria AND c.estado = :estado AND c.tipocita = :tipocita"),
    @NamedQuery(name = "Cita.findByEstado", query = "SELECT c FROM Cita c WHERE c.estado = :estado")})
public class Cita implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcita")
    private Collection<Pago> pagoCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcita")
    private Integer idcita;
    @Size(max = 45)
    @Column(name = "tipocita")
    private String tipocita;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio")
    private Float precio;
    @Size(max = 45)
    @Column(name = "fechareservacion")
    private String fechareservacion;
    @Size(max = 45)
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "idveterinario", referencedColumnName = "idveterinario")
    @ManyToOne
    private Veterinario idveterinario;
    @JoinColumn(name = "idclinicaveterinaria", referencedColumnName = "idclinicaveterinaria")
    @ManyToOne
    private Clinicaveterinaria idclinicaveterinaria;
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    @ManyToOne
    private Cliente idcliente;

    public Cita() {
    }

    public Cita(Integer idcita) {
        this.idcita = idcita;
    }

    public Integer getIdcita() {
        return idcita;
    }

    public void setIdcita(Integer idcita) {
        this.idcita = idcita;
    }

    public String getTipocita() {
        return tipocita;
    }

    public void setTipocita(String tipocita) {
        this.tipocita = tipocita;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public String getFechareservacion() {
        return fechareservacion;
    }

    public void setFechareservacion(String fechareservacion) {
        this.fechareservacion = fechareservacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Veterinario getIdveterinario() {
        return idveterinario;
    }

    public void setIdveterinario(Veterinario idveterinario) {
        this.idveterinario = idveterinario;
    }

    public Clinicaveterinaria getIdclinicaveterinaria() {
        return idclinicaveterinaria;
    }

    public void setIdclinicaveterinaria(Clinicaveterinaria idclinicaveterinaria) {
        this.idclinicaveterinaria = idclinicaveterinaria;
    }

    public Cliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Cliente idcliente) {
        this.idcliente = idcliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcita != null ? idcita.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cita)) {
            return false;
        }
        Cita other = (Cita) object;
        if ((this.idcita == null && other.idcita != null) || (this.idcita != null && !this.idcita.equals(other.idcita))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Cita[ idcita=" + idcita + " ]";
    }

    @XmlTransient
    public Collection<Pago> getPagoCollection() {
        return pagoCollection;
    }

    public void setPagoCollection(Collection<Pago> pagoCollection) {
        this.pagoCollection = pagoCollection;
    }
    
}
