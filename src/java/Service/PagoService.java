/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import WS.FlowResponse;

/**
 *
 * @author Gerald
 */
public class PagoService {

    public static FlowResponse generarLinkPago(java.lang.Integer precio, java.lang.String correo, java.lang.String urlRet) {
        WS.ServicioTR service = new WS.ServicioTR();
        WS.IServicioTR port = service.getBasicHttpBindingIServicioTR();
        return port.generarLinkPago(precio, correo, urlRet);
    }

    public static Integer getPorcentajeAnticipo() {
        WS.ServicioTR service = new WS.ServicioTR();
        WS.IServicioTR port = service.getBasicHttpBindingIServicioTR();
        return port.getPorcentajeAnticipo();
    }
    
}
