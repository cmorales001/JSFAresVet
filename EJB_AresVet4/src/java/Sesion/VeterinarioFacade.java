/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sesion;

import Entidades.Clinicaveterinaria;
import Entidades.Veterinario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author KrauseRPC
 */
@Stateless
public class VeterinarioFacade extends AbstractFacade<Veterinario> implements VeterinarioFacadeLocal {
    @PersistenceContext(unitName = "EJB_AresVet4PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VeterinarioFacade() {
        super(Veterinario.class);
    }
    
    @Override
    public Veterinario validarVeterinario(String email, String password) {
        Veterinario veterinario = null;
        try {
            Query sql = em.createNamedQuery("Veterinario.verificarUsuario").setParameter("email", email).setParameter("password", password);
            List<Veterinario> listaVeterinario = sql.getResultList();
            if(!listaVeterinario.isEmpty()){
                veterinario=listaVeterinario.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return veterinario;

    }
    
    // encontrar un veterinario de uba clinica especifica
    @Override
    public List<Veterinario> encontrarVeterinarioPorClinica(Clinicaveterinaria idClinica, String estado) {
        List<Veterinario> listaVeterinario=null;
        try {
            Query sql = em.createNamedQuery("Veterinario.encontrarPorClinicaEstado").setParameter("idclinicaveterinaria", idClinica).setParameter("estado", estado);
            listaVeterinario = sql.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return listaVeterinario;

    }
    
}
