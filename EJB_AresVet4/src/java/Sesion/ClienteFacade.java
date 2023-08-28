/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sesion;

import Entidades.Cliente;
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
public class ClienteFacade extends AbstractFacade<Cliente> implements ClienteFacadeLocal {
    @PersistenceContext(unitName = "EJB_AresVet4PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }
    
    @Override
    public Cliente validarCliente(String email, String password) {
        Cliente cli = null;
        try {
            Query sql = em.createNamedQuery("Cliente.verificarUsuario").setParameter("email", email).setParameter("password", password);
            List<Cliente> listaCliente = sql.getResultList();
            if(!listaCliente.isEmpty()){
                cli=listaCliente.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return cli;

    }
    
    @Override
    public Cliente existeCliente(String email) {
        Cliente cli = null;
        try {
            Query sql = em.createNamedQuery("Cliente.findByEmail").setParameter("email", email);
            List<Cliente> listaCliente = sql.getResultList();
            if(!listaCliente.isEmpty()){
                cli=listaCliente.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return cli;

    }
    
    
}
