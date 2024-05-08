/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia;

import java.util.List;
import modelo.Mensaje;

/**
 *
 * @author Ryzen 5
 */
public interface IMensajeDAO 
{
    List<Mensaje> consultar() throws PersistenciaException;
    void guardar(Mensaje mensaje) throws PersistenciaException;
}
