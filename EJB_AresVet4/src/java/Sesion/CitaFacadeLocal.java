/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sesion;

import Entidades.Cita;
import Entidades.Cliente;
import Entidades.Clinicaveterinaria;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KrauseRPC
 */
@Local
public interface CitaFacadeLocal {

    void create(Cita cita);

    void edit(Cita cita);

    void remove(Cita cita);

    Cita find(Object id);

    List<Cita> findAll();

    List<Cita> findRange(int[] range);

    int count();

    public List<Cita> encontrarCitaPorClinica(Clinicaveterinaria idClinica);

    public List<Cita> encontrarCitaPorClinicaEstado(Clinicaveterinaria idClinica, String estado, String tipoCita);

    public boolean encontrarCitaPorCliente(Cliente idCliente, String estado, String tipoCita);

    public Cita encontrarCitaPorCliente1(Cliente idCliente, String estado, String tipoCita);

    public List<Cita> encontrarCitaPorCliente2(Cliente idCliente);
    
}
