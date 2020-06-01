/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import WS.ArrayOfServicioTour;

/**
 *
 * @author Gerald
 */
public class ServiciosTourService {

    public static ArrayOfServicioTour buscarToursPorFechaYCiudad(javax.xml.datatype.XMLGregorianCalendar fechaInicio, javax.xml.datatype.XMLGregorianCalendar fechaFin, java.lang.Integer ciudadId) {
        WS.ServicioTR service = new WS.ServicioTR();
        WS.IServicioTR port = service.getBasicHttpBindingIServicioTR();
        return port.buscarToursPorFechaYCiudad(fechaInicio, fechaFin, ciudadId);
    }

    public static Boolean contratarServicioTour(java.lang.Short serv, java.lang.Short res) {
        WS.ServicioTR service = new WS.ServicioTR();
        WS.IServicioTR port = service.getBasicHttpBindingIServicioTR();
        return port.contratarServicioTour(serv, res);
    }

    public static ArrayOfServicioTour listarServiciosTourReserva(java.lang.Integer reservaId) {
        WS.ServicioTR service = new WS.ServicioTR();
        WS.IServicioTR port = service.getBasicHttpBindingIServicioTR();
        return port.listarServiciosTourReserva(reservaId);
    }
    
}
