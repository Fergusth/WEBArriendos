/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import WS.ArrayOfCiudad;

/**
 *
 * @author Oskardashh
 */
public class ReservaService {

    public static ArrayOfCiudad listaCiudades() {
        WS.ServicioTR service = new WS.ServicioTR();
        WS.IServicioTR port = service.getBasicHttpBindingIServicioTR();
        return port.listaCiudades();
    }
    
}
