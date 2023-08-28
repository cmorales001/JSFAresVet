/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sesion;

import Entidades.Cliente;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KrauseRPC
 */
@Local
public interface ClienteFacadeLocal {

    void create(Cliente cliente);

    void edit(Cliente cliente);

    void remove(Cliente cliente);

    Cliente find(Object id);

    List<Cliente> findAll();

    List<Cliente> findRange(int[] range);

    int count();

    public Cliente validarCliente(String email, String password);

    public Cliente existeCliente(String email);
    
}
