/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import WS.ArrayOfCiudad;
import WS.ArrayOfReserva;

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

    public static ArrayOfReserva listarReservasUsuario(java.lang.String dni) {
        WS.ServicioTR service = new WS.ServicioTR();
        WS.IServicioTR port = service.getBasicHttpBindingIServicioTR();
        return port.listarReservasUsuario(dni);
    }

    public static ArrayOfReserva listarReservasUsuarioEstado(java.lang.String dni, java.lang.String estado) {
        WS.ServicioTR service = new WS.ServicioTR();
        WS.IServicioTR port = service.getBasicHttpBindingIServicioTR();
        return port.listarReservasUsuarioEstado(dni, estado);
    }
    
}
