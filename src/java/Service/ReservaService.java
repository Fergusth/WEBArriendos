/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import WS.ArrayOfCiudad;
import WS.ArrayOfReserva;
import WS.Reserva;

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

    public static Boolean cancelarReserva(java.lang.Integer idReserva) {
        WS.ServicioTR service = new WS.ServicioTR();
        WS.IServicioTR port = service.getBasicHttpBindingIServicioTR();
        return port.cancelarReserva(idReserva);
    }

    public static Boolean pagarReservaAnticipo(java.lang.Integer idReserva, java.lang.Integer pago) {
        WS.ServicioTR service = new WS.ServicioTR();
        WS.IServicioTR port = service.getBasicHttpBindingIServicioTR();
        return port.pagarReservaAnticipo(idReserva, pago);
    }

    public static Boolean pagarReservaTotal(java.lang.Integer idReserva) {
        WS.ServicioTR service = new WS.ServicioTR();
        WS.IServicioTR port = service.getBasicHttpBindingIServicioTR();
        return port.pagarReservaTotal(idReserva);
    }
    
    public static Reserva crearReserva(javax.xml.datatype.XMLGregorianCalendar fECHACHECKIN, javax.xml.datatype.XMLGregorianCalendar fECHACHECKOUT, java.lang.String hORACHECKIN, java.lang.String hORACHECKOUT, java.lang.String uSUARIODNI, java.lang.Integer pRECIOTOTAL, java.lang.Integer tOTALPAGADO, java.lang.String eSTADO, java.lang.String fORMAPAGO) {
        WS.ServicioTR service = new WS.ServicioTR();
        WS.IServicioTR port = service.getBasicHttpBindingIServicioTR();
        return port.crearReserva(fECHACHECKIN, fECHACHECKOUT, hORACHECKIN, hORACHECKOUT, uSUARIODNI, pRECIOTOTAL, tOTALPAGADO, eSTADO, fORMAPAGO);
    }
    
}
