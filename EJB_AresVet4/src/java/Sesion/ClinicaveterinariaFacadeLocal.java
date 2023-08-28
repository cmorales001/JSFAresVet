/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sesion;

import Entidades.Clinicaveterinaria;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KrauseRPC
 */
@Local
public interface ClinicaveterinariaFacadeLocal {

    void create(Clinicaveterinaria clinicaveterinaria);

    void edit(Clinicaveterinaria clinicaveterinaria);

    void remove(Clinicaveterinaria clinicaveterinaria);

    Clinicaveterinaria find(Object id);

    List<Clinicaveterinaria> findAll();

    List<Clinicaveterinaria> findRange(int[] range);

    int count();

    public Clinicaveterinaria validarClinicaVeterinaria(String email, String password);

    public Clinicaveterinaria buscarClinicaVeterinaria(int idClinica);
    
}
