/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import WS.ArrayOfMulta;

/**
 *
 * @author Gerald
 */
public class MultasService {

    public static Boolean pagarMulta(java.lang.Integer multaId, java.lang.Integer pago) {
        WS.ServicioTR service = new WS.ServicioTR();
        WS.IServicioTR port = service.getBasicHttpBindingIServicioTR();
        return port.pagarMulta(multaId, pago);
    }

    public static Boolean tieneMultas(java.lang.String dni) {
        WS.ServicioTR service = new WS.ServicioTR();
        WS.IServicioTR port = service.getBasicHttpBindingIServicioTR();
        return port.tieneMultas(dni);
    }

    public static ArrayOfMulta listaMultasUsuario(java.lang.String dni) {
        WS.ServicioTR service = new WS.ServicioTR();
        WS.IServicioTR port = service.getBasicHttpBindingIServicioTR();
        return port.listaMultasUsuario(dni);
    }

    public static ArrayOfMulta listaMultasImpagasUsuario(java.lang.String dni) {
        WS.ServicioTR service = new WS.ServicioTR();
        WS.IServicioTR port = service.getBasicHttpBindingIServicioTR();
        return port.listaMultasImpagasUsuario(dni);
    }

    
}
