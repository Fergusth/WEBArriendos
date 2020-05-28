/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import WS.ArrayOfServicioExtra;

/**
 *
 * @author Oskardashh
 */
public class ServiciosExtraService {

    public static ArrayOfServicioExtra listarServiciosExtra() {
        WS.ServicioTR service = new WS.ServicioTR();
        WS.IServicioTR port = service.getBasicHttpBindingIServicioTR();
        return port.listarServiciosExtra();
    }

    public static ArrayOfServicioExtra listarServicioExtraReserva(java.lang.Integer idReserva) {
        WS.ServicioTR service = new WS.ServicioTR();
        WS.IServicioTR port = service.getBasicHttpBindingIServicioTR();
        return port.listarServicioExtraReserva(idReserva);
    }

    public static Boolean contratarServicioExtra(java.lang.Short serv, java.lang.Short res, java.lang.Boolean conReserva) {
        WS.ServicioTR service = new WS.ServicioTR();
        WS.IServicioTR port = service.getBasicHttpBindingIServicioTR();
        return port.contratarServicioExtra(serv, res, conReserva);
    }

    public static Boolean cancelarServicio(java.lang.Short serv, java.lang.Short res) {
        WS.ServicioTR service = new WS.ServicioTR();
        WS.IServicioTR port = service.getBasicHttpBindingIServicioTR();
        return port.cancelarServicio(serv, res);
    }
    
    
    
}
