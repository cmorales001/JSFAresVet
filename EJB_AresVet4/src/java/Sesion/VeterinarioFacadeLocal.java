/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sesion;

import Entidades.Clinicaveterinaria;
import Entidades.Veterinario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KrauseRPC
 */
@Local
public interface VeterinarioFacadeLocal {

    void create(Veterinario veterinario);

    void edit(Veterinario veterinario);

    void remove(Veterinario veterinario);

    Veterinario find(Object id);

    List<Veterinario> findAll();

    List<Veterinario> findRange(int[] range);

    int count();

    public Veterinario validarVeterinario(String email, String password);

    public List<Veterinario> encontrarVeterinarioPorClinica(Clinicaveterinaria idClinica, String estado);
    
}
