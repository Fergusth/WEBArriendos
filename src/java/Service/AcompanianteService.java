/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

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
    
    
}
