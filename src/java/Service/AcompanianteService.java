/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import WS.Acompaniante;
import WS.ArrayOfAcompaniante;

/**
 *
 * @author Oskardashh
 */
public class AcompanianteService {

    public static Boolean crearAcompaniante(java.lang.String dNI, java.lang.String nOMBRECOMPLETO, java.lang.String eXTRANJERO, java.lang.String cORREO, java.lang.Integer tELEFONO) {
        WS.ServicioTR service = new WS.ServicioTR();
        WS.IServicioTR port = service.getBasicHttpBindingIServicioTR();
        return port.crearAcompaniante(dNI, nOMBRECOMPLETO, eXTRANJERO, cORREO, tELEFONO);
    }

    public static ArrayOfAcompaniante listarAcompaniatesReserva(java.lang.Integer idReserva) {
        WS.ServicioTR service = new WS.ServicioTR();
        WS.IServicioTR port = service.getBasicHttpBindingIServicioTR();
        return port.listarAcompaniatesReserva(idReserva);
    }

    public static Acompaniante buscarAcompaniante(java.lang.String dni) {
        WS.ServicioTR service = new WS.ServicioTR();
        WS.IServicioTR port = service.getBasicHttpBindingIServicioTR();
        return port.buscarAcompaniante(dni);
    }

    public static Boolean crearAcompanianteReserva(java.lang.String dni, java.lang.Integer reserva) {
        WS.ServicioTR service = new WS.ServicioTR();
        WS.IServicioTR port = service.getBasicHttpBindingIServicioTR();
        return port.crearAcompanianteReserva(dni, reserva);
    }

    public static Boolean borrarAcompanianteReserva(java.lang.Integer reserva, java.lang.String dni) {
        WS.ServicioTR service = new WS.ServicioTR();
        WS.IServicioTR port = service.getBasicHttpBindingIServicioTR();
        return port.borrarAcompanianteReserva(reserva, dni);
    }
    
    
}
