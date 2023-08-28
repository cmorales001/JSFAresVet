/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sesion;

import Entidades.Pago;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KrauseRPC
 */
@Local
public interface PagoFacadeLocal {

    void create(Pago pago);

    void edit(Pago pago);

    void remove(Pago pago);

    Pago find(Object id);

    List<Pago> findAll();

    List<Pago> findRange(int[] range);

    int count();
    
}
