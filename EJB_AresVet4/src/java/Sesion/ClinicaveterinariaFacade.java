/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sesion;

import Entidades.Clinicaveterinaria;
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
public class ClinicaveterinariaFacade extends AbstractFacade<Clinicaveterinaria> implements ClinicaveterinariaFacadeLocal {
    @PersistenceContext(unitName = "EJB_AresVet4PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClinicaveterinariaFacade() {
        super(Clinicaveterinaria.class);
    }
    
    @Override
    public Clinicaveterinaria validarClinicaVeterinaria(String email, String password) {
        Clinicaveterinaria clinica = null;
        try {
            Query sql = em.createNamedQuery("Clinicaveterinaria.validarUsuario").setParameter("email", email).setParameter("password", password);
            List<Clinicaveterinaria> listaClinicas = sql.getResultList();
            if(!listaClinicas.isEmpty()){
                clinica=listaClinicas.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return clinica;

    }
    
    //buscar clinica
    @Override
    public Clinicaveterinaria buscarClinicaVeterinaria(int idClinica) {
        Clinicaveterinaria clinica = null;
        try {
            Query sql = em.createNamedQuery("Clinicaveterinaria.findByIdclinicaveterinaria").setParameter("idclinicaveterinaria", idClinica);
            List<Clinicaveterinaria> listaClinicas = sql.getResultList();
            if(!listaClinicas.isEmpty()){
                clinica=listaClinicas.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return clinica;

    }
    
}
